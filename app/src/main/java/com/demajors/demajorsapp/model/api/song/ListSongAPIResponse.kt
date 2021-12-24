package com.demajors.demajorsapp.model.api.song

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class ListSongAPIResponse(
    @field:SerializedName("data")
    val data: List<Song> = mutableListOf()
) : BaseAPIResponse()

data class Song(

    @field:SerializedName("cover_url")
    val coverUrl: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("tags")
    val tags: List<String?>? = null,

    @field:SerializedName("full_file_url")
    val fullFileUrl: String? = null,

    @field:SerializedName("is_deleted")
    val isDeleted: Int? = null,

    @field:SerializedName("rilisan_id")
    val rilisanId: String? = null,

    @field:SerializedName("is_activated")
    val isActivated: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("artis_info_id")
    val artisInfoId: String? = null,

    @field:SerializedName("preview_file_url")
    val previewFileUrl: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
