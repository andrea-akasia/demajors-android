package com.demajors.demajorsapp.feature.signup

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.signup.SignUpBody
import com.demajors.demajorsapp.model.api.signup.VerifyEmailBody
import com.demajors.demajorsapp.util.generateResponseApiFromErrorBody
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class SignUpViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var onSignUpSuccess = MutableLiveData<Boolean>()
    internal var onVerifySuccess = MutableLiveData<Boolean>()
    internal var isLoading = MutableLiveData<Boolean>()

    private fun loadUserInfo() {
        dataManager.getUserInfo()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                dataManager.saveUserInfo(
                                    response.data?.dataUser?.name!!,
                                    response.data.dataUser.username!!,
                                    response.data.dataUser.noTelp!!
                                )
                                onVerifySuccess.postValue(true)
                            } else {
                                Timber.w(Throwable("getUserInfo gagal: ${response.errMessage}"))
                                warningMessage.postValue("Load User Info gagal: ${response.errMessage}")
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

    fun verify(email: String, pass: String, otp: String) {
        dataManager.verifyEmail(VerifyEmailBody(otp, email, pass))
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                dataManager.saveLoginCredentials(
                                    email = email,
                                    token = response.data?.token!!,
                                    tokenRefresh = response.data.tokenRefresh!!
                                )
                                loadUserInfo()
                            } else {
                                warningMessage.postValue(response.errMessage)
                            }
                        }
                    } else {
                        // not 20x
                        val code = res.code()
                        if (code == 401) {
                            dataManager.clearPrefs()
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                warningMessage.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
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

    fun signup(email: String, pass: String) {
        isLoading.postValue(true)
        dataManager.signUp(SignUpBody(email, pass))
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    isLoading.postValue(false)
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onSignUpSuccess.postValue(true)
                            } else {
                                warningMessage.postValue(response.errMessage)
                            }
                        }
                    } else {
                        // not 20x
                        val code = res.code()
                        if (code == 401) {
                            dataManager.clearPrefs()
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                warningMessage.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
                            }
                        } else {
                            val errorBody = res.errorBody()
                            if (errorBody != null) {
                                warningMessage.postValue(generateResponseApiFromErrorBody(errorBody).errMessage)
                            }
                        }
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
