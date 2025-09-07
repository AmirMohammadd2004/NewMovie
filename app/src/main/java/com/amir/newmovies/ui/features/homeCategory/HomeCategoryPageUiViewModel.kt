package com.amir.newmovies.ui.features.homeCategory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.newmovies.model.data.Home_Category
import com.amir.newmovies.model.repository.homeCategoryRepository.HomeCategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeCategoryPageUiViewModel(

    private val homeCategoryRepository: HomeCategoryRepository

) : ViewModel() {

val homeCategory = mutableStateOf<List<Home_Category.Result>>(listOf())



    fun clearData(){

        homeCategory.value = emptyList()

    }

    fun getData(id : Int) {

        viewModelScope.launch {

            try {
                delay(400)

              homeCategory.value = homeCategoryRepository.homeCategory(id).result

            } catch (e: Exception) {


            }
        }
    }
}