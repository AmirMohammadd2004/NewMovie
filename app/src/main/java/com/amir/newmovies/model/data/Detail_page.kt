package com.amir.newmovies.model.data


import com.google.gson.annotations.SerializedName

data class Detail_page(
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
        @SerializedName("info")
        val info: Info,
        @SerializedName("reaction")
        val reaction: Reaction,
        @SerializedName("age")
        val age: Age,
        @SerializedName("movie_url")
        val movieUrl: List<MovieUrl>,
        @SerializedName("actor")
        val actor: List<Actor>,
        @SerializedName("comment")
        val comment: List<Comment>,
        @SerializedName("movie_screenshot")
        val movieScreenshot: List<MovieScreenshot>,
        @SerializedName("similar")
        val similar: List<Similar>,
        @SerializedName("more_detail")
        val moreDetail: List<MoreDetail>
    ) {
        data class Info(
            @SerializedName("id")
            val id: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("year")
            val year: String,
            @SerializedName("imdb_rate")
            val imdbRate: String,
            @SerializedName("imdb_rate_votes")
            val imdbRateVotes: String,
            @SerializedName("imdb_link")
            val imdbLink: String,
            @SerializedName("duration")
            val duration: String,
            @SerializedName("file_trailer")
            val fileTrailer: String,
            @SerializedName("banner")
            val banner: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("file_trailer_clone")
            val fileTrailerClone: String,
            @SerializedName("is_clone")
            val isClone: Int,
            @SerializedName("genre")
            val genre: String
        )

        data class Reaction(
            @SerializedName("favorite")
            val favorite: Int,
            @SerializedName("count_visit")
            val countVisit: String,
            @SerializedName("status_like_dislike")
            val statusLikeDislike: Int,
            @SerializedName("count_like")
            val countLike: Int,
            @SerializedName("count_dislike")
            val countDislike: Int,
            @SerializedName("count_comment")
            val countComment: Int,
            @SerializedName("count_comment_total")
            val countCommentTotal: Int,
            @SerializedName("count_comment_perPage")
            val countCommentPerPage: Int
        )

        data class Age(
            @SerializedName("age")
            val age: String,
            @SerializedName("imdb_rating")
            val imdbRating: String,
            @SerializedName("color")
            val color: String,
            @SerializedName("description")
            val description: String
        )

        data class MovieUrl(
            @SerializedName("season")
            val season: Int,
            @SerializedName("title")
            val title: String,   //fasl 1
            @SerializedName("episode")
            val episode: List<Episode>
        ) {
            data class Episode(
                @SerializedName("season")
                val season: Int,
                @SerializedName("episode")
                val episode: Int,
                @SerializedName("title")
                val title: String,
                @SerializedName("quality")
                val quality: List<Quality>,
                @SerializedName("openSubtitles")
                val openSubtitles: List<Any>
            ) {
                data class Quality(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("type")
                    val type: Int,
                    @SerializedName("movie_id")
                    val movieId: Int,
                    @SerializedName("movie_quality_id")
                    val movieQualityId: Int,
                    @SerializedName("season")
                    val season: Int,
                    @SerializedName("episode")
                    val episode: Int,
                    @SerializedName("quality_type")
                    val qualityType: String,
                    @SerializedName("quality_title")
                    val qualityTitle: String,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("srt")
                    val srt: String,
                    @SerializedName("srt_vtt")
                    val srtVtt: String,
                    @SerializedName("quality_image")
                    val qualityImage: String,
                    @SerializedName("url_clone")
                    val urlClone: String,
                    @SerializedName("srt_clone")
                    val srtClone: String,
                    @SerializedName("srt_vtt_clone")
                    val srtVttClone: String,
                    @SerializedName("url_name")
                    val urlName: String,
                    @SerializedName("srt_name")
                    val srtName: String,
                    @SerializedName("srt_vtt_name")
                    val srtVttName: String,
                    @SerializedName("download")
                    val download: Int,
                    @SerializedName("access_play")
                    val accessPlay: Int
                )
            }
        }

        data class Actor(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("role")
            val role: String,
            @SerializedName("role_id")
            val roleId: Int,
            @SerializedName("image")
            val image: String
        )

        data class Comment(
            @SerializedName("id")
            val id: Int,
            @SerializedName("movie_id")
            val movieId: Int,
            @SerializedName("parent_id")
            val parentId: Int,
            @SerializedName("comment")
            val comment: String,
            @SerializedName("spoiler")
            val spoiler: Int,
            @SerializedName("date")
            val date: String,
            @SerializedName("like")
            val like: Int,
            @SerializedName("dislike")
            val dislike: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("answer")
            val answer: String
        )

        data class MovieScreenshot(
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String
        )

        data class Similar(
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

        data class MoreDetail(
            @SerializedName("title")
            val title: String,
            @SerializedName("value")
            val value: String,
            @SerializedName("image")
            val image: String
        )
    }
}