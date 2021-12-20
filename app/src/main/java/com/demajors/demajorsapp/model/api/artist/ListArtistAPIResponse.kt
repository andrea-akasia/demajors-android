package com.demajors.demajorsapp.model.api.artist

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class ListArtistAPIResponse(
    @field:SerializedName("data")
    val data: List<Artist> = mutableListOf()
) : BaseAPIResponse()

data class Artist(

    @field:SerializedName("cover_url")
    val coverUrl: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("artis_id")
    val artisId: String? = null,

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("is_deleted")
    val isDeleted: Int? = null,

    @field:SerializedName("is_activated")
    val isActivated: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("info_yt")
    val infoYt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("info_spot")
    val infoSpot: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("no_telp")
    val noTelp: String? = null,

    @field:SerializedName("info_ig")
    val infoIg: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
