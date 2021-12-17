package com.demajors.demajorsapp.model.api.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileBody(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null,

    @field:SerializedName("no_telp")
    val noTelp: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
