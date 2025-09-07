package com.amir.newmovies.ui.features.homePage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.newmovies.model.data.Home_Page
import com.amir.newmovies.model.repository.homeRepository.HomePageRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomePageUiViewModel(
    private val homePageRepository: HomePageRepository

) : ViewModel() {


    val slider = mutableStateOf<List<Home_Page.Result.Slider>>(emptyList())

    val category = mutableStateOf<List<Home_Page.Result.Category>>(emptyList())


    val errorHome = mutableStateOf("")


    fun getData() {

        viewModelScope.launch {


            delay(300)


            try {


                val dataMovies = homePageRepository.getHomeData().result

                slider.value = dataMovies.slider

                category.value = dataMovies.category



            } catch (e: Exception) {
                errorHome.value = e.toString()

            }
        }
    }
}
