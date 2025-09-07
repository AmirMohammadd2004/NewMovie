package com.amir.newmovies.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amir.newmovies.di.myModules
import com.amir.newmovies.them.NewMoviesTheme
import com.amir.newmovies.ui.features.detailPage.DetailPageUi
import com.amir.newmovies.ui.features.homePage.HomePageUi
import com.amir.newmovies.ui.features.loginPage.LoginPageUi
import com.amir.newmovies.ui.features.category.CategoryPageUi
import com.amir.newmovies.ui.features.getCategory.GetCategoryPageUi
import com.amir.newmovies.ui.features.homeCategory.HomeCategoryPageUi
import com.amir.newmovies.ui.features.search.SearchPageUi
import com.amir.newmovies.ui.features.showVideo.ShowVideo
import com.amir.newmovies.util.KEY_DETAIL_PAGE
import com.amir.newmovies.util.KEY_GET_CATEGORY
import com.amir.newmovies.util.KEY_HOME_CATEGORY
import com.amir.newmovies.util.MyScreen
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        setContent {

            Koin(appDeclaration = {

                androidContext(this@MainActivity)
                modules(myModules)

            }) {

                NewMoviesTheme {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {

                        NewMovieUI()

                    }
                }
            }
        }
    }
}


@Composable
fun NewMovieUI() {

    val naveController = rememberNavController()
    KoinNavHost(
        navController = naveController,
        startDestination = MyScreen.LoginPage.rote
    ) {

        composable(
            route = MyScreen.LoginPage.rote
        ) {
            LoginPageUi()
        }

        composable(
            route = MyScreen.HomePage.rote
        ) {
            HomePageUi()

        }

        composable(
            route = "${MyScreen.DetailPage.rote}/{${KEY_DETAIL_PAGE}}",
            arguments = listOf(navArgument(KEY_DETAIL_PAGE){
                type = NavType.IntType
            })
        ) {
            DetailPageUi(it.arguments!!.getInt(KEY_DETAIL_PAGE,0))
        }

        composable(
            route = MyScreen.CategoryPage.rote
        ) {

            CategoryPageUi()
        }

        composable(
            route = MyScreen.SearchPage.rote
        ) {

            SearchPageUi()
        }


        composable(
            route = "${MyScreen.HomeCategoryPage.rote}/{${KEY_HOME_CATEGORY}}",
            arguments = listOf(navArgument(KEY_HOME_CATEGORY) {
                type = NavType.IntType
            })

            ) {
            HomeCategoryPageUi(it.arguments!!.getInt(KEY_HOME_CATEGORY, 0))
        }




        composable(
            route = "${MyScreen.GetCategoryPage.rote}/{${KEY_GET_CATEGORY}}",
            arguments = listOf(navArgument(KEY_GET_CATEGORY) {
                type = NavType.IntType
            })

        ) {
            GetCategoryPageUi(it.arguments!!.getInt(KEY_GET_CATEGORY, 0))
        }

        composable( route = MyScreen.ShowVideo.rote){

            ShowVideo()
        }
    }
}

