package com.demajors.demajorsapp.model.api.rilisan

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class ListRilisanAPIResponse(
    @field:SerializedName("data")
    val data: List<Rilisan> = mutableListOf()
) : BaseAPIResponse()

data class Rilisan(

    @field:SerializedName("cover_url")
    val coverUrl: String? = null,

    @field:SerializedName("is_preorder")
    val isPreorder: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("tags")
    val tags: List<String?>? = null,

    @field:SerializedName("is_deleted")
    val isDeleted: Int? = null,

    @field:SerializedName("is_activated")
    val isActivated: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("catalog_no")
    val catalogNo: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("genre")
    val genre: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("artis_info_id")
    val artisInfoId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("stock")
    val stock: String? = null
)
