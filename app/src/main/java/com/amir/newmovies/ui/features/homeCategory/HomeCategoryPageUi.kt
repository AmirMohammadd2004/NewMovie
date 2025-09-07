package com.amir.newmovies.ui.features.homeCategory

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.amir.newmovies.model.data.Genres
import com.amir.newmovies.util.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import com.amir.newmovies.R
import com.amir.newmovies.model.data.Get_Genress
import com.amir.newmovies.model.data.Home_Category
import com.amir.newmovies.them.brightBlack
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.NetworkChecker


@Composable
fun HomeCategoryPageUi(category: Int) {

    val viewModel = getNavViewModel<HomeCategoryPageUiViewModel>()
    val navigation = getNavController()
    val context = LocalContext.current

    if (NetworkChecker(context).isInternetConnected) {


        LaunchedEffect(category) {
            viewModel.clearData()
            viewModel.getData(category)
        }

        val data = viewModel.homeCategory.value

        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor),

            ) {

            ItemsHomeCategory(
                data = data,
                idHomeCategory = {

                    //detailPage
                    navigation.navigate(MyScreen.DetailPage.rote+"/"+it)
                }
            )

        }


    } else {

        TryNetworkHomeCategory(
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


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun ItemsHomeCategory(
    data: List<Home_Category.Result>,
    idHomeCategory:(Int) -> Unit
    ) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 60.dp , top = 30.dp)
    ) {
        items(data.size) {

            Items(data[it] , idHomeCategory = {idHomeCategory.invoke(it)} )

        }
    }


}

@Composable
fun Items(data: Home_Category.Result , idHomeCategory:(Int) -> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 30.dp )
            .clickable{ idHomeCategory.invoke(data.id) }
    ) {

        AsyncImage(
            model = data.image,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp, 170.dp)
                .clip(ShapeDefaults.Medium),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .widthIn(max = 90.dp),
            overflow = TextOverflow.Ellipsis,
            text = data.title, fontSize = 15.sp,
            color = Color.White,
            maxLines = 1

        )
        Text(
            modifier = Modifier
                .widthIn(max = 90.dp),
            overflow = TextOverflow.Ellipsis,
            text = data.name, fontSize = 14.sp,
            color = Color.White,
            maxLines = 1

        )

    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun TryNetworkHomeCategory(onTryClick: () -> Unit, context: Context) {

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