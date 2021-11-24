package com.demajors.demajorsapp.model.api.auth

import com.google.gson.annotations.SerializedName

data class LoginBody(

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("passw")
    val passw: String? = null
)
