package com.demajors.demajorsapp.feature.login

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.auth.LoginBody
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class LoginViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var isLoading = MutableLiveData<Boolean>()
    internal var isLoginSuccess = MutableLiveData<Boolean>()

    fun loginEmail(email: String, password: String) {
        isLoading.postValue(true)
        dataManager.loginEmail(LoginBody(email, password))
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    isLoading.postValue(false)
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                dataManager.saveLoginCredentials(
                                    email,
                                    response.data?.token!!,
                                    response.data.tokenRefresh!!
                                )
                                isLoginSuccess.postValue(true)
                            } else {
                                Timber.w(Throwable("Login gagal: ${response.errMessage}"))
                                warningMessage.postValue("Login gagal: ${response.errMessage}")
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
                    isLoading.postValue(false)
                    Timber.e(err)
                    warningMessage.postValue(err.message)
                }
            )
    }
}
