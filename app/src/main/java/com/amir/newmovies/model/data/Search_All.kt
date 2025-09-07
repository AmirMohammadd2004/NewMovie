package com.amir.newmovies.model.data



data class Search_All(
    val status: Int,
    val result: List<Result>,
    val `by`: String,
    val channel: String,
    val website: String
) {
    data class Result(
        val id: Int,
        val type: Int,
        val name: String,
        val status: Int,
        val movieLanguageId: Int,
        val title: String,
        val image: String
    )
}