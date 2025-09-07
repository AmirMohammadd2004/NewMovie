package com.amir.newmovies.model.repository.genresRepository

import com.amir.newmovies.model.data.Get_Genress
import com.amir.newmovies.model.net.ApiService
import com.amir.newmovies.util.emptyGenres

class GenresRepositoryIMP(
    private val apiService: ApiService
) : GenresRepository {
    override suspend fun getByGenresMovies(id: Int): Get_Genress {


        val result = apiService.getByGenres(

            id = id,
            type = "movies"

        )

        if (result.status == 200) {

            return result
        }
        return emptyGenres()
    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    override suspend fun getByGenresSeries(id: Int): Get_Genress {


        val result = apiService.getByGenres(

            id = id,
            type = "series"

        )

        if (result.status == 200) {

            return result
        }

        return emptyGenres()


    }


}