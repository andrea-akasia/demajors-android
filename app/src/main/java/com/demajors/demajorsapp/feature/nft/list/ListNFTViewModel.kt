package com.demajors.demajorsapp.feature.nft.list

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.artist.NFTItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class ListNFTViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

    fun getDummyTrendingNFTs(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        return result
    }

    fun getDummyLatestNFTs(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        return result
    }
}