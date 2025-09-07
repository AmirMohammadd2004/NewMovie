package com.amir.newmovies.model.repository.detailRepository

import com.amir.newmovies.model.data.Detail_page
import com.amir.newmovies.model.net.ApiService

class DetailRepositoryIMP(
    private val apiService: ApiService
) : DetailRepository {

    override suspend fun getDataMovie(id: Int, emptyList: (String) -> Unit): Detail_page {
        val result = apiService.detailPage(id = id)

        return if (result.status == 200) {
            result
        } else {
            emptyList.invoke("لیست خالی میباشد")
            result
        }
    }
}
