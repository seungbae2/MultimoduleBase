package com.sb.multimodule.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val mwDispatcher: MwDispatchers)

enum class MwDispatchers {
    Default,
    IO,
}