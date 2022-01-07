package com.demajors.demajorsapp.model.api.profile.address

import com.google.gson.annotations.SerializedName

data class UpdateUserAddressBody(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("is_deleted")
    val isDeleted: Boolean? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("is_default")
    val isDefault: Boolean? = null,

    @field:SerializedName("lat")
    val latitude: Double? = null,

    @field:SerializedName("long")
    val longitude: Double? = null
)
