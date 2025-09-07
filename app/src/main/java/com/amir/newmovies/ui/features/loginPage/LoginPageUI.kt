package com.amir.newmovies.ui.features.loginPage

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amir.newmovies.R
import com.amir.newmovies.util.MyScreen
import com.amir.newmovies.util.NetworkChecker
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel


@Composable
fun LoginPageUi() {

    val navigation = getNavController()
    val context = LocalContext.current
    val viewModel = getViewModel<LoginPageViewModel>()
    viewModel.shoAnim()



    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Animation(

            viewModel = viewModel,
            navigation = navigation,
            context = context,
        ) {

            if (viewModel.navigation.value == true) {
                if (NetworkChecker(context).isInternetConnected) {
                    navigation.navigate(MyScreen.HomePage.rote) {
                        popUpTo(MyScreen.LoginPage.rote) {
                            inclusive = true
                        }
                    }

                } else {

                    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun Animation(
    viewModel: LoginPageViewModel,
    navigation: NavHostController,
    context: Context,
    onButtonClick: () -> Unit
) {


    Column(
        modifier = Modifier.padding(bottom = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.shoAnimation1.value) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                color = Color.White

            ) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.login_anim2)
                )
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.BottomCenter,
                    iterations = 1,
                    speed = .5f,
                )
            }

            Card(
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(horizontal = 16.dp)
                    .clip(ShapeDefaults.ExtraLarge),
                colors = CardDefaults.cardColors(Color.DarkGray),


                ) {


                if (viewModel.shoAnimation2.value) {
                    val composition by rememberLottieComposition(
                        LottieCompositionSpec.RawRes(R.raw.login_anim)
                    )
                    val anim = LottieAnimation(
                        composition = composition,
                        modifier = Modifier.fillMaxSize(),
                        iterations = 1

                    )
                }
            }
        }


        if (viewModel.navigation.value == true) {
            if (NetworkChecker(context).isInternetConnected) {
                navigation.navigate(MyScreen.HomePage.rote) {
                    popUpTo(MyScreen.LoginPage.rote) {
                        inclusive = true
                    }
                }

            } else {

                Toast.makeText(context, "لطفا اینترنت خود را متصل کنید", Toast.LENGTH_SHORT).show()

                Button(
                    { onButtonClick.invoke() },
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
    }


}

///////////////////////////////////////////////////////////////////////////////////////////////////


