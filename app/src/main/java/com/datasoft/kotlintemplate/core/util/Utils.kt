package com.datasoft.kotlintemplate.core.util

import com.google.gson.annotations.SerializedName

data class WrapperListResponse<T>(
    var code: Int,
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : String,
    @SerializedName("errors") val errors : List<String>? = null,
    @SerializedName("data") var data : List<T>? = null
)
data class WrapperResponse<T>(
    var code: Int,
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("errors") val errors : List<String>? = null,
    @SerializedName("data") var data : T? = null
)