package com.demajors.demajorsapp.feature.merchandise

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import javax.inject.Inject

@SuppressLint("CheckResult")
class MerchandiseViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
}
