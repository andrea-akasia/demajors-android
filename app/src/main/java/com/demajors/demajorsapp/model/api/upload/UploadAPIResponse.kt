package com.demajors.demajorsapp.model.api.upload

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import com.google.gson.annotations.SerializedName

data class UploadAPIResponse(
    @field:SerializedName("data")
    val data: String? = null
) : BaseAPIResponse()
