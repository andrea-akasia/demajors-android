package com.demajors.demajorsapp.feature.myartist.detail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.artist.MerchandiseItem
import com.demajors.demajorsapp.model.artist.NFTItem
import com.demajors.demajorsapp.model.artist.RilisanItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class ArtistViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {
    internal var warningMessage = MutableLiveData<String>()

    fun getDummyArtistMerchandises(): MutableList<MerchandiseItem> {
        val result = mutableListOf<MerchandiseItem>()
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        result.add(MerchandiseItem())
        return result
    }

    fun getDummyArtistReleases(): MutableList<RilisanItem> {
        val result = mutableListOf<RilisanItem>()
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        result.add(RilisanItem())
        return result
    }

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
