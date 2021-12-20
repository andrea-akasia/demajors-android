package com.demajors.demajorsapp.feature.myartist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.artist.Artist
import com.demajors.demajorsapp.model.home.ArtistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@SuppressLint("CheckResult")
class MyArtistViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    lateinit var artistFlow: Flow<PagingData<Artist>>

    fun loadListData() {
        artistFlow = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ArtistPagingDataSource(dataManager) }
        ).flow.cachedIn(viewModelScope)
    }

    fun getDummyArtistItems(): MutableList<ArtistItem> {
        val result = mutableListOf<ArtistItem>()
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        result.add(ArtistItem())
        return result
    }
}
