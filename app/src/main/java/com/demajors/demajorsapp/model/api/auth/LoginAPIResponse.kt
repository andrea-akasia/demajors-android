package com.demajors.demajorsapp.model.api.auth

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class LoginAPIResponse(
    @field:SerializedName("data")
    val data: LoginData? = null
) : BaseAPIResponse()

data class LoginData(

    @field:SerializedName("tokenRefresh")
    val tokenRefresh: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)
