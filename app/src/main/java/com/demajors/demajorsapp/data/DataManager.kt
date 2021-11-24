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
import com.demajors.demajorsapp.model.api.auth.LoginAPIResponse
import com.demajors.demajorsapp.model.api.auth.LoginBody
import com.demajors.demajorsapp.model.api.auth.RefreshTokenAPIResponse
import com.demajors.demajorsapp.model.api.detailpokemon.DetailPokemonResponse
import com.demajors.demajorsapp.util.Const.Companion.KEY_EMAIL
import com.demajors.demajorsapp.util.Const.Companion.KEY_IS_LOGGED_IN
import com.demajors.demajorsapp.util.Const.Companion.KEY_TOKEN
import com.demajors.demajorsapp.util.Const.Companion.KEY_TOKEN_REFRESH

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

    fun refreshToken(): Single<Response<RefreshTokenAPIResponse>> {
        return api.refreshToken(BuildConfig.AUTH_URL + "v1/token/refresh", getRefreshAuthorizationHeader())
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
