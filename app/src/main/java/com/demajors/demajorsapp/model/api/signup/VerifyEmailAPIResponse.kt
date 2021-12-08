package com.demajors.demajorsapp.model.api.signup

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class VerifyEmailAPIResponse(
    @field:SerializedName("data")
    val data: Data? = null
) : BaseAPIResponse()

data class Data(

    @field:SerializedName("tokenRefresh")
    val tokenRefresh: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)
