package com.amir.newmovies.util

import com.amir.newmovies.model.data.Detail_page
import com.amir.newmovies.model.data.Get_Genress
import com.amir.newmovies.model.data.Home_Category
import com.amir.newmovies.model.data.Home_Page
import com.amir.newmovies.model.data.Search_All


sealed class MyScreen(val rote: String) {

    object LoginPage : MyScreen("LoginPageUI")

    object HomePage : MyScreen("HomePageUI")

    object DetailPage : MyScreen("DetailPageUI")

    object CategoryPage : MyScreen("CategoryPageUI")

    object GetCategoryPage : MyScreen("GetCategoryPageUI")

    object SearchPage : MyScreen("SearchPageUI")

    object HomeCategoryPage : MyScreen("HomeCategoryPageUi")


    object ShowVideo : MyScreen("ShowVideoPag")

}


//////////////////////////////////////////////////////////////////////////////////


const val KEY_GET_CATEGORY = "category_ky"
const val KEY_HOME_CATEGORY = "HomeCategory_ky"
const val KEY_DETAIL_PAGE = "Detail_ky"


//////////////////////////////////////////////////////////////////////////////////


fun emptySearch(): Search_All {

    return Search_All(

        status = 0,
        result = emptyList(),
        by = "",
        channel = "",
        website = ""
    )
}


fun emptyHome(): Home_Page {

    return Home_Page(
        status = 0,
        result = Home_Page.Result(

            slider = emptyList(),
            category = emptyList()
        ),
        by = "",
        channel = "",
        website = ""
    )


}

fun emptyGenres(): Get_Genress {

    return Get_Genress(

        status = 0,
        result = Get_Genress.Result(

            slider = emptyList(),
            category = emptyList()
        ),
        by = "",
        channel = "",
        website = ""
    )

}


fun emptyHomeCategory(): Home_Category {

    return Home_Category(
        status = 0,
        by = "",
        channel = "",
        website = "",
        result = emptyList()
    )
}


fun emptyInfo(): Detail_page.Result.Info {

    return Detail_page.Result.Info(
        id = 0,
        type = 0,
        name = "",
        year = "",
        imdbRate = "",
        imdbRateVotes = "",
        imdbLink = "",
        duration = "",
        fileTrailer = "",
        banner = "",
        title = "",
        description = "",
        image = "",
        fileTrailerClone = "",
        isClone = 0,
        genre = ""

    )


}

//////////////////////////////////////////////////////////////////////////////////

//   TOKEN AND BASE URL
const val TOKEN = "wgsisjz7ro9ocbu:ZkHNNlPSUQIHxGnWOVAH"

const val BASE_URL = "https://api.majidapi.ir/"

//////////////////////////////////////////////////////////////////////////////////