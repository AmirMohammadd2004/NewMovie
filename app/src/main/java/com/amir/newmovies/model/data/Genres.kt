package com.amir.newmovies.model.data



data class Genres(
    val status: Int, //200
    val result: Result,
    val `by`: String,
    val channel: String,
    val website: String
) {
    data class Result(
        val movies: List<Movy>,
        val series: List<Sery>
    ) {
        data class Movy(
            val id: Int,
            val title: String
        )

        data class Sery(
            val id: Int,
            val title: String
        )
    }
}