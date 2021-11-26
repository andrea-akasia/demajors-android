package com.demajors.demajorsapp.model.api.auth

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class UserInfoAPIResponse(
    @field:SerializedName("data")
    val data: Data? = null
) : BaseAPIResponse()

data class DataUser(

    @field:SerializedName("is_activated")
    val isActivated: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("no_telp")
    val noTelp: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("passw")
    val passw: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)

data class Data(
    @field:SerializedName("dataUser")
    val dataUser: DataUser? = null
)
