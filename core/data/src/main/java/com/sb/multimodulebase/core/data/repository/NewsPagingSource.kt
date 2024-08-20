package com.sb.multimodulebase.core.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodulebase.core.data.mapper.toExternalData
import com.sb.multimodulebase.core.model.Article
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

internal class NewsPagingSource(
    private val newsNetworkDataSource: NewsNetworkDataSource,
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: INIT_PAGE
            when (val response =
                newsNetworkDataSource.getTopHeadlines("us", PAGE_SIZE, currentPage)) {
                is ApiResponse.Failure -> {
                    LoadResult.Error(Throwable(response.message()))
                }

                is ApiResponse.Success -> {
                    val data = response.data.articles.map { it.toExternalData() }

                    LoadResult.Page(
                        data = data,
                        prevKey = (currentPage - 1).takeIf { it >= INIT_PAGE },
                        nextKey = (currentPage + 1).takeIf { response.data.articles.isNotEmpty() },
                    )
                }
            }
        } catch (excception: Exception) {
            return LoadResult.Error(excception)
        }
    }

    companion object {
        const val INIT_PAGE = 1
        const val PAGE_SIZE = 50
    }
}