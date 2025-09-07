package com.amir.newmovies.ui.features.loginPage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginPageViewModel() : ViewModel() {

    val shoAnimation1 = mutableStateOf(false)
    val shoAnimation2 = mutableStateOf(false)
    val navigation = mutableStateOf(false)
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    fun shoAnim() {
        viewModelScope.launch {
            shoAnimation1.value = true
            delay(1500)
            shoAnimation2.value = true
            delay(4000)
            navigation.value=true
        }

    }












}