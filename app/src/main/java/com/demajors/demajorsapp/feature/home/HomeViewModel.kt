package com.demajors.demajorsapp.feature.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.banner.Banner
import javax.inject.Inject

@SuppressLint("CheckResult")
class HomeViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

    fun getDummyBanner(): MutableList<Banner>{
        val result = mutableListOf<Banner>()
        result.add(Banner())
        result.add(Banner())
        result.add(Banner())
        return result
    }
}
