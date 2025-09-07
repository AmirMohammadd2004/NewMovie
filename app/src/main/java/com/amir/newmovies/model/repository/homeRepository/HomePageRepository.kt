package com.amir.newmovies.model.repository.homeRepository

import com.amir.newmovies.model.data.Home_Page

interface HomePageRepository {

    suspend fun getHomeData(): Home_Page




}