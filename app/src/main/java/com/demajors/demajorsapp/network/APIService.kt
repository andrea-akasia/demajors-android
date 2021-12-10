package com.demajors.demajorsapp.network

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.demajors.demajorsapp.model.api.auth.LoginAPIResponse
import com.demajors.demajorsapp.model.api.auth.LoginBody
import com.demajors.demajorsapp.model.api.auth.RefreshTokenAPIResponse
import com.demajors.demajorsapp.model.api.auth.UserInfoAPIResponse
import com.demajors.demajorsapp.model.api.pokemon.PokemonResponse
import com.demajors.demajorsapp.model.api.detailpokemon.DetailPokemonResponse
import com.demajors.demajorsapp.model.api.signup.SignUpBody
import com.demajors.demajorsapp.model.api.signup.VerifyEmailAPIResponse
import com.demajors.demajorsapp.model.api.signup.VerifyEmailBody
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import retrofit2.http.Header

interface APIService {
    @POST
    fun verifyEmail(
        @Url authURL: String,
        @Body body: VerifyEmailBody
    ): Single<Response<VerifyEmailAPIResponse>>

    @POST("v1/user/signup")
    fun signUp(
        @Body body: SignUpBody
    ): Single<Response<BaseAPIResponse>>

    @GET("v1/user/info")
    fun getUserInfo(
        @Header("Authorization") token: String
    ): Single<Response<UserInfoAPIResponse>>

    @POST
    fun logout(
        @Url authURL: String,
        @Header("Authorization") refreshToken: String
    ): Single<Response<BaseAPIResponse>>

    @POST
    fun refreshToken(
        @Url authURL: String,
        @Header("Authorization") token: String,
    ): Single<Response<RefreshTokenAPIResponse>>

    @POST
    fun verifyOTPLogin(
        @Url authURL: String,
        @Body body: VerifyEmailBody
    ): Single<Response<LoginAPIResponse>>

    @POST("v1/user/login/otp/email")
    fun loginEmailWithOTP(
        @Body body: LoginBody,
    ): Single<Response<BaseAPIResponse>>

    @POST
    fun loginEmail(
        @Url authURL: String,
        @Body body: LoginBody,
    ): Single<Response<LoginAPIResponse>>

    @GET("pokemon/{name}")
    fun requestDetailPokemon(
        @Path("name") name: String
    ): Single<Response<DetailPokemonResponse>>

    @GET("pokemon")
    fun requestListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") page: Int
    ): Single<Response<PokemonResponse>>
}
