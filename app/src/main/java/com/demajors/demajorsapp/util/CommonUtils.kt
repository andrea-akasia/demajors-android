package com.demajors.demajorsapp.util

import android.annotation.SuppressLint
import com.demajors.demajorsapp.model.api.BaseAPIResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getCurrentTimeISO(time: Long): String {
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
    df.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

    return df.format(Date(time))
}

fun generateResponseApiFromErrorBody(errorBody: ResponseBody): BaseAPIResponse {
    val obj = JSONObject(errorBody.string())
    return BaseAPIResponse(
        errMessage = obj.getString("errMessage"),
        isSucceed = obj.getBoolean("isSucceed")
    )
}

fun createMultipartFromImageFile(file: File, key: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        key,
        file.name,
        file.asRequestBody("image/jpeg".toMediaTypeOrNull())
    )
}
