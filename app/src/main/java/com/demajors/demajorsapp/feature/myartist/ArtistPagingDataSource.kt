package com.demajors.demajorsapp.feature.myartist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.artist.Artist
import timber.log.Timber

class ArtistPagingDataSource(private val dataManager: DataManager) :
    PagingSource<Int, Artist>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val pageNumber = params.key ?: 1
        return try {
            val response = dataManager.getListPagedArtist(pageNumber, params.loadSize)
            val pagedResponse = response.body()
            val data = pagedResponse?.data
            Timber.d("data size is ${data?.size}")

            val nextPageNumber: Int = pageNumber + 1
            // TODO need next page from api
            /*if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }*/

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int = 1
}
