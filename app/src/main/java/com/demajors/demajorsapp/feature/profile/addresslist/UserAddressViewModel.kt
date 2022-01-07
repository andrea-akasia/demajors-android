package com.demajors.demajorsapp.feature.profile.addresslist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.profile.address.CreateUserAddressBody
import com.demajors.demajorsapp.model.api.profile.address.UpdateUserAddressBody
import com.demajors.demajorsapp.model.api.profile.address.UserAddress
import com.demajors.demajorsapp.util.generateResponseApiFromErrorBody
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class UserAddressViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var onAuthFailed = MutableLiveData<String>()
    internal var onDataLoaded = MutableLiveData<List<UserAddress>>()
    internal var onDataCreated = MutableLiveData<Boolean>()
    internal var onDataDeleted = MutableLiveData<Boolean>()

    fun delete(data: UserAddress) {
        dataManager.deleteUserAddress(
            UpdateUserAddressBody(
                id = data.id,
                address = data.address,
                isDeleted = true,
                isDefault = data.isDefault == 1,
                latitude = data.latitude!!.toDouble(),
                longitude = data.longitude!!.toDouble()
            )
        )
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onDataDeleted.postValue(true)
                            } else {
                                Timber.w(Throwable("deleteUserAddress gagal: ${response.errMessage}"))
                                warningMessage.postValue("Hapus Data gagal: ${response.errMessage}")
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

    fun create(lat: Double, lng: Double, address: String) {
        dataManager.createUserAddress(CreateUserAddressBody(address = address, lat = lat, lng = lng))
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onDataCreated.postValue(true)
                            } else {
                                Timber.w(Throwable("createUserAddress gagal: ${response.errMessage}"))
                                warningMessage.postValue("Create Data gagal: ${response.errMessage}")
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

    fun loadData() {
        dataManager.getListUserAddress()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onDataLoaded.postValue(response.data)
                            } else {
                                Timber.w(Throwable("getListUserAddress gagal: ${response.errMessage}"))
                                warningMessage.postValue("Load Data gagal: ${response.errMessage}")
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
}
