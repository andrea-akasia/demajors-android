package com.demajors.demajorsapp.util

import com.demajors.demajorsapp.model.api.BaseAPIResponse
import okhttp3.ResponseBody
import org.json.JSONObject

fun generateResponseApiFromErrorBody(errorBody: ResponseBody): BaseAPIResponse {
    val obj = JSONObject(errorBody.string())
    return BaseAPIResponse(
        errMessage = obj.getString("errMessage"),
        isSucceed = obj.getBoolean("isSucceed")
    )
}
