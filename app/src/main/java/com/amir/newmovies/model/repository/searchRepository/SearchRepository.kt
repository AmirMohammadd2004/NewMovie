package com.amir.newmovies.model.repository.searchRepository

import com.amir.newmovies.model.data.Search_All

interface SearchRepository {


    suspend fun searchMovies( search: String ): Search_All
}