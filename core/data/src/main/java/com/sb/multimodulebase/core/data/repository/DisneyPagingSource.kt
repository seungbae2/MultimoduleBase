package com.sb.multimodulebase.core.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sb.multimodule.core.network.DisneyNetworkDataSource
import com.sb.multimodulebase.core.data.mapper.toExternalData
import com.sb.multimodulebase.core.model.DisneyCharacter
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import okio.IOException

class DisneyPagingSource(
    private val disneyNetworkDataSource: DisneyNetworkDataSource
) : PagingSource<Int,DisneyCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, DisneyCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DisneyCharacter> {
        return try {
            val currentPage = params.key ?: 1
            when (val response = disneyNetworkDataSource.getAllCharacters(currentPage, PAGE_SIZE)) {
                is ApiResponse.Failure -> {
                    LoadResult.Error(Throwable(response.message()))
                }
                is ApiResponse.Success -> {
                    val data = response.data.data.map { it.toExternalData() }
                    val nextPageUrl = response.data.info.nextPage
                    val nextPageKey = nextPageUrl.substringAfterLast("=").toIntOrNull()
                    val prevPageKey = response.data.info.previousPage.substringAfterLast("=").toIntOrNull()

                    LoadResult.Page(
                        data = data,
                        prevKey = prevPageKey,
                        nextKey = nextPageKey,
                    )
                }
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}