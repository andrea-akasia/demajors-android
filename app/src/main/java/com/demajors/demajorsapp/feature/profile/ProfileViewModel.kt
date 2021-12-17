package com.demajors.demajorsapp.feature.profile

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.auth.DataUser
import com.demajors.demajorsapp.util.generateResponseApiFromErrorBody
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class ProfileViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var onLogoutSuccess = MutableLiveData<Boolean>()
    internal var onUserInfoLoaded = MutableLiveData<DataUser>()
    internal var onAuthFailed = MutableLiveData<String>()

    fun getUsername() = dataManager.getUsername()
    fun isLoggedIn(): Boolean = dataManager.getLoginState()

    fun loadUserInfo() {
        dataManager.getUserInfo()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onUserInfoLoaded.postValue(response.data?.dataUser!!)
                            } else {
                                Timber.w(Throwable("getUserInfo gagal: ${response.errMessage}"))
                                warningMessage.postValue("Load User Info gagal: ${response.errMessage}")
                            }
                        }
                    } else {
                        // not 20x
                        val code = res.code()
                        if (code != 401) {
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                warningMessage.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
                            }
                        } else {
                            dataManager.clearPrefs()
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                onAuthFailed.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
                            } else {
                                onAuthFailed.postValue("please relogin")
                            }
                        }
                    }
                },
                { err ->
                    Timber.e(err)
                    warningMessage.postValue(err.message)
                }
            )
    }

    fun logout() {
        dataManager.logout()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                dataManager.clearPrefs()
                                onLogoutSuccess.postValue(true)
                            } else {
                                Timber.w(Throwable("Logout gagal: ${response.errMessage}"))
                                warningMessage.postValue("Logout gagal: ${response.errMessage}")
                            }
                        }
                    } else {
                        // not 20x
                        val code = res.code()
                        warningMessage.postValue("Server Error $code")
                        Timber.w(Throwable("Server Error $code"))
                    }
                },
                { err ->
                    Timber.e(err)
                    warningMessage.postValue(err.message)
                }
            )
    }
}
