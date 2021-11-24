package com.demajors.demajorsapp.model.api.auth

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class RefreshTokenAPIResponse(
    @field:SerializedName("data")
    val data: RefreshTokenData? = null
) : BaseAPIResponse()

data class RefreshTokenData(
    @field:SerializedName("token")
    val token: String? = null
)
