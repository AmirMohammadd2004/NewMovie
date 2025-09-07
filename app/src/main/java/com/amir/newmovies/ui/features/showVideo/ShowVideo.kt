// ShowVideo.kt
package com.amir.newmovies.ui.features.showVideo

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.NetworkChecker
import dev.burnoo.cokoin.navigation.getNavViewModel

@OptIn(UnstableApi::class)
@Composable
fun ShowVideo() {
    val context = LocalContext.current
    val activity = context as? Activity
    val viewModel = getNavViewModel<ShowVideoViewModel>()
    val isConnected = NetworkChecker(context).isInternetConnected
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    var currentPosition by rememberSaveable { mutableStateOf(viewModel.currentPosition) }

    // آماده سازی پلیر وقتی URL تغییر می‌کند یا اینترنت وصل است
    LaunchedEffect(viewModel.videoUrl.value, viewModel.subtitleUrl.value, isConnected) {
        if (isConnected && viewModel.videoUrl.value.isNotEmpty()) {
            viewModel.preparePlayer()
        }
    }

    // مدیریت Lifecycle پلیر
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP -> {
                    viewModel.savePosition()
                    viewModel.exoPlayer?.pause()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    viewModel.releasePlayer()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            viewModel.releasePlayer()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // 1️⃣ کنترل خودکار Landscape + Fullscreen هنگام ورود و بازگرداندن حالت هنگام خروج
    DisposableEffect(activity) {
        // ورود → Landscape + Fullscreen
// ورود → Landscape دوطرفه + Fullscreen
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        activity?.window?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                insetsController?.let { controller ->
                    controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    controller.systemBarsBehavior =
                        android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } else {
                @Suppress("DEPRECATION")
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        onDispose {
            // خروج → Orientation آزاد + نمایش Status & Navigation
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            activity?.window?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                } else {
                    @Suppress("DEPRECATION")
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                }
            }
        }
    }

    if (!isConnected) {
        TryNetworkShowVideo(
            onTryClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    Toast.makeText(context, "اینترنت متصل شد", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_SHORT).show()
                }
            },
            context = context
        )
    } else {
        // نمایش پلیر
        viewModel.exoPlayer?.let { player ->
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        this.player = player
                        useController = true
                        keepScreenOn = true // جلوگیری از خاموش شدن صفحه
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        setBackgroundColor(android.graphics.Color.BLACK) // اضافه شد
                        layoutParams = if (isLandscape) {
                            FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT
                            )
                        } else {
                            FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                (300 * ctx.resources.displayMetrics.density).toInt()
                            )
                        }
                    }
                },
                modifier = if (isLandscape) Modifier.fillMaxSize() else Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}

@Composable
fun TryNetworkShowVideo(onTryClick: () -> Unit, context: android.content.Context) {
    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_LONG).show()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor),
        contentAlignment = Alignment.Center
    ) {
        // فقط اطلاع‌رسانی، بدون دکمه
        Text("اتصال اینترنت برقرار نیست", color = androidx.compose.ui.graphics.Color.White)
    }
}
