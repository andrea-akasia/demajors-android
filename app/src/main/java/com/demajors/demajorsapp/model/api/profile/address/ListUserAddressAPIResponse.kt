package com.demajors.demajorsapp.model.api.profile.address

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class ListUserAddressAPIResponse(
    @field:SerializedName("data")
    val data: List<UserAddress> = mutableListOf()
) : BaseAPIResponse()

data class UserAddress(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("is_activated")
    val isActivated: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("is_default")
    val isDefault: Int? = null,

    @field:SerializedName("lat")
    val latitude: String? = null,

    @field:SerializedName("long")
    val longitude: String? = null
)
