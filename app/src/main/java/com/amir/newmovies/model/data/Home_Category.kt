package com.amir.newmovies.model.data


import com.google.gson.annotations.SerializedName

data class Home_Category(
    @SerializedName("status")
    val status: Int,
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("by")
    val `by`: String,
    @SerializedName("channel")
    val channel: String,
    @SerializedName("website")
    val website: String
) {
    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("type")
        val type: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("image")
        val image: String
    )
}