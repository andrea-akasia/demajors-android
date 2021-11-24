package com.demajors.demajorsapp.feature.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.util.generateResponseApiFromErrorBody
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class MainViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var onAuthFailed = MutableLiveData<String>()

    fun isLoggedIn() = dataManager.getLoginState()

    fun refreshToken() {
        dataManager.refreshToken()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                dataManager.updateToken(response.data?.token!!)
                            } else {
                                onAuthFailed.postValue(response.errMessage)
                            }
                        }
                    } else {
                        // not 20x
                        val code = res.code()
                        if (code == 401) {
                            dataManager.clearPrefs()
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                onAuthFailed.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
                            } else {
                                onAuthFailed.postValue("please relogin")
                            }
                        } else {
                            warningMessage.postValue("Server Error $code")
                            Timber.w(Throwable("Server Error $code"))
                        }
                    }
                },
                { err ->
                    Timber.e(err)
                    warningMessage.postValue(err.message)
                }
            )
    }
}
