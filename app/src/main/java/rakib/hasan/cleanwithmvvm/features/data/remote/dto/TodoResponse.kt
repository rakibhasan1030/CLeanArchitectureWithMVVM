package rakib.hasan.cleanwithmvvm.features.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("completed") var isCompleted: Boolean
)
