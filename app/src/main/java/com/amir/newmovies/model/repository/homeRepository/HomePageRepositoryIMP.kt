package com.amir.newmovies.model.repository.homeRepository

import com.amir.newmovies.model.data.Home_Page
import com.amir.newmovies.model.net.ApiService
import com.amir.newmovies.util.emptyHome

class HomePageRepositoryIMP(

    private val apiService: ApiService

) : HomePageRepository {
    override suspend fun getHomeData(): Home_Page {

        val result = apiService.homePage(

            type = "movies",
        )

        if (result.status == 200) {
            return result
        }

        return emptyHome()
    }

}