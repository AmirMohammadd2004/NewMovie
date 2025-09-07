package com.amir.newmovies.model.repository.genresRepository

import com.amir.newmovies.model.data.Get_Genress


interface GenresRepository {


    suspend fun getByGenresMovies(id : Int ) : Get_Genress

    suspend fun getByGenresSeries(id : Int ) : Get_Genress


}