package com.amir.newmovies.ui.features.category

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.amir.newmovies.model.data.Genres
import com.amir.newmovies.util.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import com.amir.newmovies.R
import com.amir.newmovies.them.back
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.NetworkChecker


@Composable
fun CategoryPageUi() {

    val viewModel = getNavViewModel<CategoryPageUiViewModel>()
    val navigation = getNavController()
    val context = LocalContext.current


    if (NetworkChecker(context).isInternetConnected) {

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
                TopBarCategory()

                CategoryList(
                    onItemClick = {

                        navigation.navigate(MyScreen.GetCategoryPage.rote+ "/" +it){
                            popUpTo(MyScreen.CategoryPage.rote)
                        }

                    }
                )
            }




            NavigationViewCategory(
                searchOnClick = {
                    navigation.navigate(MyScreen.SearchPage.rote) {
                        popUpTo(MyScreen.HomePage.rote) {
                            inclusive == true
                        }
                    }
                },
                listOnClick = {

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
        TryNetworkCategory(
            onTryClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    navigation.navigate(MyScreen.CategoryPage.rote) {
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
fun CategoryList(onItemClick:(Int)-> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 12.dp)

    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(1) }
                    .clip(ShapeDefaults.Large),
            ) {
                AsyncImage(
                    model = R.drawable.action,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "اکشن",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {onItemClick.invoke(2) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.majarajoii,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "ماجراجویی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(3) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.comedy,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "کمدی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,

                    )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(4) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.haiejani,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "هیجانی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(5) }
                    .clip(ShapeDefaults.Large),
            ) {
                AsyncImage(
                    model = R.drawable.elmitakhayoli,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "علمی_تخیلی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(6) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.animation,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "انیمیشن",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {onItemClick.invoke(7) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.fantezi,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "فانتزی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,

                    )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {onItemClick.invoke(8) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.love,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "عاشقانه",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(9)}
                    .clip(ShapeDefaults.Large),
            ) {
                AsyncImage(
                    model = R.drawable.horrore,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "ترسناک",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {onItemClick.invoke(10) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.deram,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "درام",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(11) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.jenaii,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "جنایی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,

                    )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(12) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.jangi,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "جنگی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(13) }
                    .clip(ShapeDefaults.Large),
            ) {
                AsyncImage(
                    model = R.drawable.zendeginame,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "زندگی نامه",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(14) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.khanevadegi,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "خانوادگی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(15) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.tarikhijpg,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "تاریخی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,

                    )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(16) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.music,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "موسیقی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(17) }
                    .clip(ShapeDefaults.Large),
            ) {
                AsyncImage(
                    model = R.drawable.moamaii,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "معمایی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(18) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.varzeshi,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "ورزشی",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(19) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.western,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "وسترن",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,

                    )
            }



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onItemClick.invoke(49) }
                    .clip(ShapeDefaults.Large)
            ) {
                AsyncImage(
                    model = R.drawable.anime,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(155.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .background(back),
                    text = "انیمه",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun CardListMovies(data: Genres.Result.Movy) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {


        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = R.drawable.img,
                contentDescription = null
            )
            Text(
                text = data.title
            )
        }

        Box(
            contentAlignment = Alignment.BottomCenter

        ) {
            AsyncImage(
                model = R.drawable.img,
                contentDescription = null
            )
            Text(
                text = data.title
            )
        }


    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun NavigationViewCategory(
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


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { listOnClick.invoke() }
            ) {
                Icon(
                    Icons.Default.List,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
            Text("دسته بندی", fontSize = 12.sp, fontWeight = FontWeight.Bold , color = Color.White)
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


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCategory() {

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun TryNetworkCategory(onTryClick: () -> Unit, context: Context) {

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

