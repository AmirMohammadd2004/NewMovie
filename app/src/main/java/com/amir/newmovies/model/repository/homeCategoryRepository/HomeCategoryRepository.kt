package com.amir.newmovies.model.repository.homeCategoryRepository

import com.amir.newmovies.model.data.Home_Category

interface HomeCategoryRepository {

    suspend fun homeCategory(id: Int): Home_Category
}