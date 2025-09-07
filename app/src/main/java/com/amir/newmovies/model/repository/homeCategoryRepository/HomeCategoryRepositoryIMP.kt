package com.amir.newmovies.model.repository.homeCategoryRepository

import com.amir.newmovies.model.data.Home_Category
import com.amir.newmovies.model.net.ApiService
import com.amir.newmovies.util.emptyHomeCategory

class HomeCategoryRepositoryIMP(

    private val apiService: ApiService
)
    : HomeCategoryRepository {
    override suspend fun homeCategory(id: Int): Home_Category {


        val result = apiService.homeCategory(

            id = id
        )

        if ( result.status==200){

            return result
        }

        return emptyHomeCategory()
    }
}