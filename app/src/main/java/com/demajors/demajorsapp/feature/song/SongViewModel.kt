package com.demajors.demajorsapp.feature.song

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.home.HomeItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class SongViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

    fun getDummyHomeItems(): MutableList<HomeItem> {
        val result = mutableListOf<HomeItem>()
        result.add(HomeItem())
        result.add(HomeItem())
        result.add(HomeItem())
        result.add(HomeItem())
        result.add(HomeItem())
        result.add(HomeItem())
        return result
    }
}
