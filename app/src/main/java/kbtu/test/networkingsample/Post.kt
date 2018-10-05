package kbtu.test.networkingsample

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") var userId: Int?,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String?,
    @SerializedName("body") var body: String?
)