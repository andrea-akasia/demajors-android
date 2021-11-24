package com.demajors.demajorsapp.model.api

import com.google.gson.annotations.SerializedName

open class BaseAPIResponse(
    @SerializedName("errMessage")
    var errMessage: String = "",

    @SerializedName("isSucceed")
    var isSucceed: Boolean = false
)
