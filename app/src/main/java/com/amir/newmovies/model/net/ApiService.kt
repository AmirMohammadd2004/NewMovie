package com.amir.newmovies.model.net


import com.amir.newmovies.model.data.Detail_page
import com.amir.newmovies.model.data.Get_Genress
import com.amir.newmovies.model.data.Home_Category
import com.amir.newmovies.model.data.Home_Page
import com.amir.newmovies.model.data.Search_All
import com.amir.newmovies.util.BASE_URL
import com.amir.newmovies.util.TOKEN
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("movie/movilix")
    suspend fun getBySearch(
        @Query("action") action: String = "search",
        @Query("s") search: String,
        @Query("page") page: Int,
        @Query("token") token: String = TOKEN

    ): Search_All


///////////////////////////////////////////////////////////////////////////////////////////////////////


    @GET("movie/movilix")
    suspend fun homePage(
        @Query("action") action: String = "home",
        @Query("type") type: String,
        @Query("token") token: String = TOKEN
    ): Home_Page


    @GET("movie/movilix")
    suspend fun getByGenres(
        @Query("action") action: String = "genre",
        @Query("id") id: Int,
        @Query("type") type: String,
        @Query("token") token: String = TOKEN
    ): Get_Genress


    @GET("movie/movilix")
    suspend fun homeCategory(
        @Query("action") action: String = "category",
        @Query("id") id: Int,
        @Query("page") page: Int = 1,
        @Query("token") token: String = TOKEN

    ): Home_Category




    @GET("movie/movilix")
    suspend fun detailPage(
        @Query("action") action: String = "details",
        @Query("id") id: Int,
        @Query("token") token: String = TOKEN
    ): Detail_page


}


///////////////////////////////////////////////////////////////////////////////
fun createApiService(): ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}






