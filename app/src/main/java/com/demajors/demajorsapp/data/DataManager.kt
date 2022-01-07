package com.demajors.demajorsapp.data

import com.demajors.demajorsapp.data.local.pokemon.LocalPokemon
import com.demajors.demajorsapp.model.api.pokemon.PokemonResponse
import com.demajors.demajorsapp.network.APIService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import androidx.paging.DataSource
import com.demajors.demajorsapp.BuildConfig
import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.demajors.demajorsapp.model.api.artist.ListArtistAPIResponse
import com.demajors.demajorsapp.model.api.auth.LoginAPIResponse
import com.demajors.demajorsapp.model.api.auth.LoginBody
import com.demajors.demajorsapp.model.api.auth.RefreshTokenAPIResponse
import com.demajors.demajorsapp.model.api.auth.UserInfoAPIResponse
import com.demajors.demajorsapp.model.api.detailpokemon.DetailPokemonResponse
import com.demajors.demajorsapp.model.api.profile.UpdateProfileBody
import com.demajors.demajorsapp.model.api.profile.address.CreateUserAddressBody
import com.demajors.demajorsapp.model.api.profile.address.ListUserAddressAPIResponse
import com.demajors.demajorsapp.model.api.profile.address.UpdateUserAddressBody
import com.demajors.demajorsapp.model.api.rilisan.ListRilisanAPIResponse
import com.demajors.demajorsapp.model.api.signup.SignUpBody
import com.demajors.demajorsapp.model.api.signup.VerifyEmailAPIResponse
import com.demajors.demajorsapp.model.api.signup.VerifyEmailBody
import com.demajors.demajorsapp.model.api.song.ListSongAPIResponse
import com.demajors.demajorsapp.model.api.upload.UploadAPIResponse
import com.demajors.demajorsapp.util.Const.Companion.KEY_EMAIL
import com.demajors.demajorsapp.util.Const.Companion.KEY_IS_LOGGED_IN
import com.demajors.demajorsapp.util.Const.Companion.KEY_NAME
import com.demajors.demajorsapp.util.Const.Companion.KEY_PHONE
import com.demajors.demajorsapp.util.Const.Companion.KEY_TOKEN
import com.demajors.demajorsapp.util.Const.Companion.KEY_TOKEN_REFRESH
import com.demajors.demajorsapp.util.Const.Companion.KEY_USERNAME
import okhttp3.MultipartBody

@Singleton
class DataManager
@Inject constructor(
    private val api: APIService,
    private val prefs: PreferencesHelper,
    private val localDatabase: AppDatabase
) {

    /* ---------------------------------------- SQLite ------------------------------------------ */

    fun saveAllPokemonToLocal(listPokemon: List<LocalPokemon>): Completable {
        return Completable.fromAction {
            localDatabase.PokemonDao().saveAllPokemon(listPokemon)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun loadAllPokemonFromLocal(): DataSource.Factory<Int, LocalPokemon> {
        return localDatabase.PokemonDao().loadAllPokemonPaged()
    }

    /* -------------------------------------- Preferences --------------------------------------- */

    fun getEmail(): String = prefs.getString(KEY_EMAIL, "")!!
    fun getName(): String = prefs.getString(KEY_NAME, "")!!
    fun getUsername(): String = prefs.getString(KEY_USERNAME, "")!!
    fun getPhone(): String = prefs.getString(KEY_PHONE, "")!!

    fun saveUserInfo(name: String, username: String, phone: String) {
        prefs.putString(KEY_NAME, name)
        prefs.putString(KEY_USERNAME, username)
        prefs.putString(KEY_PHONE, phone)
    }

    fun saveLoginCredentials(email: String, token: String, tokenRefresh: String) {
        prefs.putString(KEY_EMAIL, email)
        prefs.putString(KEY_TOKEN, token)
        prefs.putString(KEY_TOKEN_REFRESH, tokenRefresh)
        prefs.putBoolean(KEY_IS_LOGGED_IN, true)
    }

    fun updateToken(token: String) {
        prefs.putString(KEY_TOKEN, token)
    }

    fun getLoginState(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN)

    fun clearPrefs() {
        prefs.putBoolean(KEY_IS_LOGGED_IN, false)
        prefs.putString(KEY_TOKEN, "")
    }

    private fun getAuthorizationHeader(): String = "Bearer " + prefs.getString(KEY_TOKEN)
    private fun getRefreshAuthorizationHeader(): String = "Bearer " + prefs.getString(KEY_TOKEN_REFRESH)

    /* ---------------------------------------- Network ----------------------------------------- */
    fun deleteUserAddress(body: UpdateUserAddressBody): Single<Response<BaseAPIResponse>> {
        return api.deleteUserAddress(getAuthorizationHeader(), body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createUserAddress(body: CreateUserAddressBody): Single<Response<BaseAPIResponse>> {
        return api.createUserAddress(getAuthorizationHeader(), body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListUserAddress(): Single<Response<ListUserAddressAPIResponse>> {
        return api.getListUserAddress(getAuthorizationHeader())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListSongForHome(): Single<Response<ListSongAPIResponse>> {
        return api.getListSongForHome(page = 1, limit = 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListRilisanForHome(): Single<Response<ListRilisanAPIResponse>> {
        return api.getListRilisanForHome(page = 1, limit = 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListArtistForHome(): Single<Response<ListArtistAPIResponse>> {
        return api.getListArtistForHome(page = 1, limit = 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    suspend fun getListPagedArtist(page: Int, limit: Int) = api.getListPagedArtist(page, limit)

    fun updateProfile(body: UpdateProfileBody): Single<Response<BaseAPIResponse>> {
        return api.updateProfile(getAuthorizationHeader(), body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun uploadImage(file: MultipartBody.Part): Single<Response<UploadAPIResponse>> {
        return api.uploadFile(getAuthorizationHeader(), file)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun verifyOTPLogin(body: VerifyEmailBody): Single<Response<LoginAPIResponse>> {
        return api.verifyOTPLogin(BuildConfig.AUTH_URL + "v1/user/email/login", body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun verifyEmail(body: VerifyEmailBody): Single<Response<VerifyEmailAPIResponse>> {
        return api.verifyEmail(BuildConfig.AUTH_URL + "v1/user/email/verif", body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun signUp(body: SignUpBody): Single<Response<BaseAPIResponse>> {
        return api.signUp(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserInfo(): Single<Response<UserInfoAPIResponse>> {
        return api.getUserInfo(getAuthorizationHeader())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout(): Single<Response<BaseAPIResponse>> {
        return api.logout(BuildConfig.AUTH_URL + "v1/token/revoke", getRefreshAuthorizationHeader())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun refreshToken(): Single<Response<RefreshTokenAPIResponse>> {
        return api.refreshToken(BuildConfig.AUTH_URL + "v1/token/refresh", getRefreshAuthorizationHeader())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginEmailWithOTP(body: LoginBody): Single<Response<BaseAPIResponse>> {
        return api.loginEmailWithOTP(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginEmail(body: LoginBody): Single<Response<LoginAPIResponse>> {
        return api.loginEmail(BuildConfig.AUTH_URL + "v0/user/email/login", body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun reqPokemon(page: Int, limit: Int): Single<Response<PokemonResponse>> {
        return api.requestListPokemon(limit, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun reqDetailPokemon(name: String): Single<Response<DetailPokemonResponse>> {
        return api.requestDetailPokemon(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
