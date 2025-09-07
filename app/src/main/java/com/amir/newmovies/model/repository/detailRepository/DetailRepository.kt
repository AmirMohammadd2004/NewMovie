package com.amir.newmovies.model.repository.detailRepository

import com.amir.newmovies.model.data.Detail_page

interface DetailRepository {



    suspend fun getDataMovie (id: Int,emptyList:(String)-> Unit): Detail_page

}