package com.demajors.demajorsapp.model.api.profile.address

import com.google.gson.annotations.SerializedName

data class CreateUserAddressBody(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("lat")
    val lat: Double? = null,

    @field:SerializedName("long")
    val lng: Double? = null
)
