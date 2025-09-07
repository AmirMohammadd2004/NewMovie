package com.amir.newmovies.ui.features.search

import android.content.Context
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amir.newmovies.model.data.Search_All
import com.amir.newmovies.util.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.delay
import com.amir.newmovies.R
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.NetworkChecker
import com.amir.newmovies.util.emptySearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher


@Composable
fun SearchPageUi() {

    val viewModel = getNavViewModel<SearchPageUiViewModel>()
    val navigation = getNavController()
    val context = LocalContext.current

    if (NetworkChecker(context).isInternetConnected) {

        val textSearch = remember { mutableStateOf("") }


        LaunchedEffect(textSearch.value) {
            delay(150)
            viewModel.clearData()
            if (textSearch.value.isNotEmpty() && textSearch.value.isNotBlank()) {
                delay(150)
                viewModel.searchByUser(textSearch.value)
            }
        }


        if (viewModel.error.value.isNotEmpty() && viewModel.error.value.isNotBlank()) {
            Toast.makeText(context, viewModel.error.value, Toast.LENGTH_LONG).show()
        }


        val data = viewModel.dataBySearchUser.value



        data == emptySearch().result
        data.isEmpty()


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.BottomCenter,

            ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 110.dp)
            ) {
                TopBarSearch()


                SearchUi(
                    value = textSearch.value,
                    onValueChange = { textSearch.value = it },
                    hint = "جستجو"
                )

                Spacer(modifier = Modifier.height(20.dp))

                GetBySearch(
                    data = data,
                    searchId = {

                        //detailPage
                        navigation.navigate(MyScreen.DetailPage.rote + "/" + it)

                    },
                    textField = textSearch
                )


            }
            NavigationViewSearch(
                searchOnClick = {

                },
                listOnClick = {
                    navigation.navigate(MyScreen.CategoryPage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive == true
                        }
                    }
                },
                homeOnClick = {
                    navigation.navigate(MyScreen.HomePage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive == true
                        }
                    }
                })
        }


    } else {
        TryNetworkSearch(
            onTryClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    navigation.navigate(MyScreen.SearchPage.rote) {
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
fun GetBySearch(
    data: List<Search_All.Result>,
    searchId: (Int) -> Unit,
    textField: MutableState<String>
) {

    if (textField.value.isNotEmpty()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center

        ) {
            items(data.size) {

                CardItem(
                    data = data[it], onClickSearch = {
                        searchId.invoke(it)
                    })

            }
        }

    } else {

        data.isEmpty()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)


        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.empty_video)
            )
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(200.dp),
                iterations = LottieConstants.IterateForever
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "برای جستجوی فیلم مورد علاقه خود نام یا قسمتی از آن را وارد کنید .",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun CardItem(data: Search_All.Result, onClickSearch: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .clickable { onClickSearch.invoke(data.id) },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .widthIn(max = 200.dp),
                overflow = TextOverflow.Ellipsis,
                text = data.title, fontWeight = FontWeight.Bold, fontSize = 18.sp,
                color = Color.White,
                maxLines = 1

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .widthIn(max = 200.dp),
                overflow = TextOverflow.Ellipsis,
                text = data.name, fontSize = 13.sp,
                color = Color.White,
                maxLines = 1

            )
        }

        if (data.image.isNotEmpty()) {
            AsyncImage(
                model = data.image,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(ShapeDefaults.Medium),
                contentDescription = null
            )
        } else {
            AsyncImage(
                model = R.drawable.img,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(ShapeDefaults.Medium),
                contentDescription = null
            )
        }


    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun SearchUi(value: String, onValueChange: (String) -> Unit, hint: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White

        ),
        shape = ShapeDefaults.Large,
        placeholder = {

            Text(
                text = hint,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    textAlign = TextAlign.Right
                ),
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null
            )
        },
        textStyle = TextStyle(
            textAlign = TextAlign.Right,
            textDirection = TextDirection.Rtl
        )
    )
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun NavigationViewSearch(
    searchOnClick: () -> Unit,
    listOnClick: () -> Unit,
    homeOnClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 45.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            IconButton(
                onClick = { searchOnClick.invoke() }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
            Text("جستجو", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
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


        IconButton(
            onClick = { homeOnClick.invoke() }
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Gray
            )
        }

    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch() {

    TopAppBar(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.11f)
            .padding(top = 4.dp),
        colors = TopAppBarDefaults.topAppBarColors(mainColor),

        title = {
            Text(
                "New Movies", fontSize = 19.sp,
                modifier = Modifier.padding(start = 110.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    )

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun TryNetwork(onTryClick: () -> Unit, context: Context) {

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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun TryNetworkSearch(onTryClick: () -> Unit, context: Context) {

    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_LONG).show()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
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
