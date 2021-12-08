package com.demajors.demajorsapp.model.api.signup

import com.google.gson.annotations.SerializedName

data class VerifyEmailBody(
    @field:SerializedName("otp")
    val otp: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("passw")
    val passw: String? = null
)
