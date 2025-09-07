package com.amir.newmovies.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.amir.newmovies.model.net.createApiService
import com.amir.newmovies.model.repository.detailRepository.DetailRepository
import com.amir.newmovies.model.repository.detailRepository.DetailRepositoryIMP
import com.amir.newmovies.model.repository.genresRepository.GenresRepository
import com.amir.newmovies.model.repository.genresRepository.GenresRepositoryIMP
import com.amir.newmovies.model.repository.homeCategoryRepository.HomeCategoryRepository
import com.amir.newmovies.model.repository.homeCategoryRepository.HomeCategoryRepositoryIMP
import com.amir.newmovies.model.repository.homeRepository.HomePageRepository
import com.amir.newmovies.model.repository.homeRepository.HomePageRepositoryIMP
import com.amir.newmovies.model.repository.searchRepository.SearchRepository
import com.amir.newmovies.model.repository.searchRepository.SearchRepositoryIMP
import com.amir.newmovies.ui.features.category.CategoryPageUiViewModel
import com.amir.newmovies.ui.features.detailPage.DetailPageUiViewModel
import com.amir.newmovies.ui.features.getCategory.GetCategoryPageUiViewModel
import com.amir.newmovies.ui.features.homeCategory.HomeCategoryPageUiViewModel
import com.amir.newmovies.ui.features.homePage.HomePageUiViewModel
import com.amir.newmovies.ui.features.loginPage.LoginPageViewModel
import com.amir.newmovies.ui.features.search.SearchPageUiViewModel
import com.amir.newmovies.ui.features.showVideo.ShowVideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    // ApiService
    single { createApiService() }


    //ExoPlayer
    single { ExoPlayer.Builder(androidContext() ).build() }



    // Repository
    single<SearchRepository> { SearchRepositoryIMP(get()) }
    single<HomePageRepository> { HomePageRepositoryIMP(get()) }
    single<GenresRepository> { GenresRepositoryIMP(get()) }
    single<HomeCategoryRepository> { HomeCategoryRepositoryIMP(get()) }
    single<DetailRepository> { DetailRepositoryIMP(get()) }


    //  ViewModel
    viewModel { GetCategoryPageUiViewModel(get()) }
    viewModel { CategoryPageUiViewModel(get()) }
    viewModel { SearchPageUiViewModel(get()) }
    viewModel { HomePageUiViewModel(get()) }
    viewModel { LoginPageViewModel() }
    viewModel { HomeCategoryPageUiViewModel(get()) }
    viewModel{ ShowVideoViewModel(androidContext()) }
    viewModel{ DetailPageUiViewModel(get() , get()  ,get() ) }
}
