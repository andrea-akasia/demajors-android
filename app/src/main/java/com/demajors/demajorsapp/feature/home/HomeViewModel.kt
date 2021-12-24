package com.demajors.demajorsapp.feature.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.artist.Artist
import com.demajors.demajorsapp.model.api.rilisan.Rilisan
import com.demajors.demajorsapp.model.api.song.Song
import com.demajors.demajorsapp.model.artist.NFTItem
import com.demajors.demajorsapp.model.banner.Banner
import com.demajors.demajorsapp.model.home.HomeItem
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class HomeViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()
    internal var onArtistLoaded = MutableLiveData<List<Artist>>()
    internal var onRilisanLoaded = MutableLiveData<List<Rilisan>>()
    internal var onSongLoaded = MutableLiveData<List<Song>>()

    fun loadListSong() {
        dataManager.getListSongForHome()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onSongLoaded.postValue(response.data)
                            } else {
                                Timber.w(Throwable("loadListSong gagal: ${response.errMessage}"))
                                warningMessage.postValue("error: ${response.errMessage}")
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

    fun loadListRilisan() {
        dataManager.getListRilisanForHome()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onRilisanLoaded.postValue(response.data)
                            } else {
                                Timber.w(Throwable("getListRilisanForHome gagal: ${response.errMessage}"))
                                warningMessage.postValue("error: ${response.errMessage}")
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

    fun loadListArtist() {
        dataManager.getListArtistForHome()
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { response ->
                            if (response.isSucceed) {
                                onArtistLoaded.postValue(response.data)
                            } else {
                                Timber.w(Throwable("getListArtistForHome gagal: ${response.errMessage}"))
                                warningMessage.postValue("error: ${response.errMessage}")
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

    fun getDummyPremiumNFTs(): MutableList<NFTItem> {
        val result = mutableListOf<NFTItem>()
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        result.add(NFTItem(type = "PREMIUM"))
        return result
    }

    fun getDummyBanner(): MutableList<Banner> {
        val result = mutableListOf<Banner>()
        result.add(Banner())
        result.add(Banner())
        result.add(Banner())
        return result
    }

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
