package id.akasia.demajorsandroid.feature.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import id.akasia.demajorsandroid.base.BaseViewModel
import id.akasia.demajorsandroid.data.DataManager
import javax.inject.Inject

@SuppressLint("CheckResult")
class MainViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
}
