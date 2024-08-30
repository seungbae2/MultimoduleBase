package com.sb.multimodulebase.core.data.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.sb.multimodule.core.common.network.Dispatcher
import com.sb.multimodule.core.common.network.MwDispatchers
import com.sb.multimodule.core.common.network.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility for reporting current location of the device.
 * It emits updates whenever the location changes.
 */
interface LocationMonitor {
    val currentLocation: Flow<Location>
}

@Singleton
internal class LocationBroadcastMonitor @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope appScope: CoroutineScope,
    @Dispatcher(MwDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : LocationMonitor {

    @SuppressLint("MissingPermission")
    override val currentLocation: SharedFlow<Location> =
        callbackFlow {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    trySend(location)
                }

                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0f,
                locationListener
            )

            // Optionally, send the last known location if available
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                trySend(it)
            }

            awaitClose {
                locationManager.removeUpdates(locationListener)
            }
        }
            .distinctUntilChanged()
            .conflate()
            .flowOn(ioDispatcher)
            .shareIn(appScope, SharingStarted.WhileSubscribed(5_000), 1)
}