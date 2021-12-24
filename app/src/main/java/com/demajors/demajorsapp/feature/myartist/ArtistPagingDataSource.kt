package com.demajors.demajorsapp.feature.myartist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.artist.Artist
import java.lang.Exception

class ArtistPagingDataSource(private val dataManager: DataManager) :
    PagingSource<Int, Artist>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val pageNumber = params.key ?: 1
            val response = dataManager.getListPagedArtist(pageNumber, params.loadSize)
            val pagedResponse = response.body()
            val prevKey = if (pageNumber == 1) null else pageNumber - 1

            LoadResult.Page(
                data = pagedResponse?.data!!,
                prevKey = prevKey,
                nextKey = if (pagedResponse.data.isNotEmpty()) pageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int = 1
}
