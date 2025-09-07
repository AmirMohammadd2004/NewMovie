package com.amir.newmovies.ui.features.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.newmovies.model.data.Search_All
import com.amir.newmovies.model.repository.searchRepository.SearchRepository
import com.amir.newmovies.util.emptySearch
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchPageUiViewModel(

    private val searchRepository: SearchRepository

) : ViewModel() {


    val dataBySearchUser = mutableStateOf<List<Search_All.Result>>(emptyList())

    val error = mutableStateOf("")



    fun clearData(){

        dataBySearchUser.value = emptyList()

    }

    fun searchByUser(search: String) {

        viewModelScope.launch {

            delay(300)

            try {
                dataBySearchUser.value = searchRepository.searchMovies(search).result

            } catch (e: Exception) {
                error.value = e.toString()
            }

        }

    }
}