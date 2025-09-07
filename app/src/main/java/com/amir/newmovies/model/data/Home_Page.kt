package com.amir.newmovies.model.data

import com.google.gson.annotations.SerializedName

data class Home_Page(
    @SerializedName("status")
    val status: Int,
    @SerializedName("result")
    val result: Result,
    @SerializedName("by")
    val `by`: String,
    @SerializedName("channel")
    val channel: String,
    @SerializedName("website")
    val website: String
) {
    data class Result(
        @SerializedName("slider")
        val slider: List<Slider>,
        @SerializedName("category")
        val category: List<Category>
    ) {
        data class Slider(
            @SerializedName("id")
            val id: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("url_trailer")
            val urlTrailer: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("url_trailer_clone")
            val urlTrailerClone: String
        )

        data class Category(
            @SerializedName("id")
            val id: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("action")
            val action: Int,
            @SerializedName("section")
            val section: Int,
            @SerializedName("genre_id")
            val genreId: Int,
            @SerializedName("record_id")
            val recordId: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("detail")
            val detail: List<Detail>
        ) {
            data class Detail(
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
    }
}