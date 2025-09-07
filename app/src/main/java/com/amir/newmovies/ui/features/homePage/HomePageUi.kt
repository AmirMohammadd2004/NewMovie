package com.amir.newmovies.ui.features.homePage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.airbnb.lottie.model.content.GradientFill
import com.amir.newmovies.model.data.Home_Page
import com.amir.newmovies.them.brightBlack
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.MyScreen
import com.amir.newmovies.util.NetworkChecker
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.nio.file.WatchEvent


@Composable
fun HomePageUi() {

    val viewModel = getNavViewModel<HomePageUiViewModel>()
    val navigation = getNavController()
    val context = LocalContext.current

    if (NetworkChecker(context).isInternetConnected) {
        viewModel.getData()




        LaunchedEffect(viewModel.errorHome.value) {
            if (viewModel.errorHome.value.isNotEmpty() && viewModel.errorHome.value.isNotBlank()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, viewModel.errorHome.value, Toast.LENGTH_LONG).show()
                }
            }
        }


        val slider = viewModel.slider.value
        val category = viewModel.category.value

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor),
            contentAlignment = Alignment.BottomCenter,

            ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 110.dp)

            ) {


                BodyHome(
                    data = category,
                    dataSlider = slider,

                    idHomeImage = {
                        //detailPage
                        navigation.navigate(MyScreen.DetailPage.rote + "/" + it)
                    },
                    idHomeCategory = {
                        navigation.navigate(MyScreen.HomeCategoryPage.rote + "/" + it)
                    },
                    idSliderClick = {
                        //detailPage
                        navigation.navigate(MyScreen.DetailPage.rote + "/" + it)

                    }
                )


            }
            NavigationViewHome(
                searchOnClick = {
                    navigation.navigate(MyScreen.SearchPage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive == true
                        }
                    }

                },
                listOnClick = {
                    navigation.navigate(MyScreen.CategoryPage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive == true
                        }
                    }
                },
                homeOnClick = {


                })

        }
    } else {
        TryNetworkHome(
            onTryClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    navigation.navigate(MyScreen.HomePage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive = true
                        }
                    }
                } else {
                    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید .", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            context = context
        )

    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun BodyHome(
    data: List<Home_Page.Result.Category>,
    dataSlider: List<Home_Page.Result.Slider>,
    idHomeImage: (Int) -> Unit,
    idHomeCategory: (Int) -> Unit,
    idSliderClick: (Int) -> Unit

) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {


        item {

            SliderHome(
                data = dataSlider,
                onClickSlider = {
                    idSliderClick.invoke(it)
                }
            )
        }

        items(data.size) {

            ItemBody(
                data[it],
                idHomeImage = { idHomeImage.invoke(it) },
                idHomeCategory = { idHomeCategory.invoke(it) }
            )

        }

    }

}


@Composable
fun ItemBody(
    dataBody: Home_Page.Result.Category,
    idHomeImage: (Int) -> Unit,
    idHomeCategory: (Int) -> Unit
) {


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.End
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    idHomeCategory.invoke(dataBody.id)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = null
            )

            Text(
                text = dataBody.title, fontWeight = FontWeight.Bold, fontSize = 18.sp,
                color = Color.White
            )

        }


        Spacer(modifier = Modifier.height(8.dp))


        LazyRow {

            items(dataBody.detail.take(8).size) {

                ImageLazy(dataBody.detail[it], idHomeImage = { idHomeImage.invoke(it) })
            }

        }
    }
}


@Composable
fun ImageLazy(data: Home_Page.Result.Category.Detail, idHomeImage: (Int) -> Unit) {


    AsyncImage(
        model = data.image,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .size(120.dp, 180.dp)
            .clip(ShapeDefaults.Medium)
            .clickable { idHomeImage.invoke(data.id) },
        contentDescription = null
    )


}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun SliderHome(data: List<Home_Page.Result.Slider>, onClickSlider: (Int) -> Unit) {


    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { data.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
    ) { page ->

        SliderItem(

            image = data[page],
            onClickSlider = { onClickSlider.invoke(it) }
        )
    }
}

@Composable
fun SliderItem(image: Home_Page.Result.Slider, onClickSlider: (Int) -> Unit) {

    if (image.image.isNotEmpty()) {
        Box(
            modifier = Modifier
                .clickable { onClickSlider.invoke(image.id) }
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = image.image,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brightBlack)


            ) {
                Text(
                    text = image.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 17.sp,
                    maxLines = 1
                )

                Text(
                    text = image.title, color = Color.White,
                    fontSize = 15.sp,
                    maxLines = 1
                )

            }
        }
    } else {

    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun NavigationViewHome(
    searchOnClick: () -> Unit,
    listOnClick: () -> Unit,
    homeOnClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 45.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {


        IconButton(
            onClick = { searchOnClick.invoke() }
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Gray
            )
        }



        IconButton(
            onClick = { listOnClick.invoke() }
        ) {
            Icon(
                Icons.Default.List,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Gray
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            IconButton(
                onClick = { homeOnClick.invoke() }
            ) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
            Text("خانه", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun TryNetworkHome(onTryClick: () -> Unit, context: Context) {

    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_LONG).show()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            { onTryClick.invoke() },
            modifier = Modifier
                .size(300.dp, 80.dp)
                .padding(top = 30.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp
            ),
            colors = ButtonDefaults.buttonColors(Color.DarkGray)
        ) {

            Text(
                " تلاش مجدد ", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}
