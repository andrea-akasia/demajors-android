package com.demajors.demajorsapp.feature.profile.mynft

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.artist.NFTItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class MyNFTViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

    fun getDummyUnopened(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "REGULAR"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "REGULAR"))
        return result
    }
}
