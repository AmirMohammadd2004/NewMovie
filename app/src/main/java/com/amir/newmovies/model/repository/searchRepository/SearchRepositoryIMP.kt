package com.amir.newmovies.model.repository.searchRepository

import com.amir.newmovies.model.data.Search_All
import com.amir.newmovies.model.net.ApiService
import com.amir.newmovies.util.emptySearch

class SearchRepositoryIMP(
    val apiService: ApiService
) : SearchRepository {


    override suspend fun searchMovies(search: String): Search_All {


        val result = apiService.getBySearch(

            search = search,
            page = 1
        )

        if (result.status == 200) {

            return result
        }

        return emptySearch()

    }

}