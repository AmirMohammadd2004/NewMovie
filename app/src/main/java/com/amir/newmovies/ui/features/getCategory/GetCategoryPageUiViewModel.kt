package com.amir.newmovies.ui.features.getCategory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.newmovies.model.data.Get_Genress
import com.amir.newmovies.model.repository.genresRepository.GenresRepository
import com.amir.newmovies.util.emptyGenres
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GetCategoryPageUiViewModel(
    private val getGenresRepository: GenresRepository

) : ViewModel() {

    val slider = mutableStateOf<List<Get_Genress.Result.Slider>>(listOf())
    val category = mutableStateOf<List<Get_Genress.Result.Category>>(listOf())



    fun clearData() {
        slider.value = emptyGenres().result.slider
        category.value = emptyGenres().result.category
    }


    fun getGenres(id: Int) {

        viewModelScope.launch {

            try {

                delay(500)

                val dataMovies = getGenresRepository.getByGenresMovies(id)

                slider.value = dataMovies.result.slider

                category.value = dataMovies.result.category


            } catch (e: Exception) {


            }
        }
    }
}