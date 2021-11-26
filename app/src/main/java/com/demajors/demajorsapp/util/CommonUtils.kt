package com.demajors.demajorsapp.util

import android.annotation.SuppressLint
import com.demajors.demajorsapp.model.api.BaseAPIResponse
import okhttp3.ResponseBody
import org.json.JSONObject
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
