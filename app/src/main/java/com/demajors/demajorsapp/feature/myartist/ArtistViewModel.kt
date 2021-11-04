package com.demajors.demajorsapp.feature.myartist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.artist.NFTItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class ArtistViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {
    internal var warningMessage = MutableLiveData<String>()

    fun getDummyPremiumNFTs(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        return result
    }

    fun getDummyRegularNFTs(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        return result
    }
}
