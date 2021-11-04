package com.demajors.demajorsapp.feature.myartist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.home.ArtistItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class MyArtistViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

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
