package com.amir.newmovies.ui.features.detailPage

import android.content.Context
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.amir.newmovies.model.data.Detail_page
import com.amir.newmovies.them.brightBlack
import com.amir.newmovies.them.mainColor
import com.amir.newmovies.util.NetworkChecker
import com.amir.newmovies.util.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import com.amir.newmovies.ui.features.showVideo.ShowVideoViewModel

@Composable
fun DetailPageUi(idMovie: Int) {
    val detailViewModel = getNavViewModel<DetailPageUiViewModel>()
    val showVideoViewModel = getNavViewModel<ShowVideoViewModel>()
    val context = LocalContext.current
    val navigation = getNavController()

    BackHandler(enabled = detailViewModel.isFullScreen.value) {
        detailViewModel.stopPlayer()
    }

    DisposableEffect(Unit) {
        onDispose { detailViewModel.savePosition() }
    }

    if (!NetworkChecker(context).isInternetConnected) {
        TryNetworkDetail(
            onTryClick = {
                if (!NetworkChecker(context).isInternetConnected) {
                    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_SHORT).show()
                }
            },
            context = context
        )
        return
    }

    LaunchedEffect(idMovie) {
        detailViewModel.clearData()
        detailViewModel.getMovieData(idMovie)
    }

    val movieInfo by detailViewModel.movieInfo
    val movieUrl by detailViewModel.movieUrl
    var selectedSeason by remember { mutableStateOf(detailViewModel.selectedSeason) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(bottom = 60.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (movieInfo.image.isNotEmpty()) {
                item { HeaderSection(movieInfo) }
            }

            when (movieInfo.type) {
                0 -> { // فیلم
                    val qualities = movieUrl.firstOrNull()?.episode?.firstOrNull()?.quality.orEmpty()
                    if (qualities.isNotEmpty()) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(

                                    text = "کیفیت‌ها و دوبله‌ها:",
                                    style = TextStyle(
                                        textDirection = TextDirection.Rtl
                                    ),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                )
                            }

                        }
                        items(qualities) { quality ->
                            QualityItem(quality) { url, srt ->
                                showVideoViewModel.setVideo(url, srt)
                                navigation.navigate(MyScreen.ShowVideo.rote) {
                                    popUpTo(MyScreen.DetailPage.rote)
                                }
                            }
                        }
                    }
                }
                1 -> { // سریال
                    if (movieUrl.isNotEmpty()) {
                        item {
                            SeasonsTabs(
                                seasons = movieUrl,
                                selectedIndex = selectedSeason,
                                onSeasonSelected = {
                                    selectedSeason = it
                                    detailViewModel.selectedSeason = it
                                }
                            )
                        }

                        val episodes = movieUrl.getOrNull(selectedSeason)?.episode.orEmpty()
                        items(episodes) { episode ->
                            EpisodeItem(episode) { url, srt ->
                                showVideoViewModel.setVideo(url, srt)
                                navigation.navigate(MyScreen.ShowVideo.rote) {
                                    popUpTo(MyScreen.DetailPage.rote)
                                }
                            }
                            Divider(
                                color = Color.Gray.copy(alpha = 0.3f),
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection(data: Detail_page.Result.Info) {
    Box(contentAlignment = Alignment.BottomCenter) {
        AsyncImage(
            model = data.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brightBlack)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = data.title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = data.name,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        InfoText(text = "${data.imdbRate} IMDB")
        InfoText(text = data.duration)
        InfoText(text = "${data.year} Year")
        InfoText(text = data.imdbRateVotes)
    }

    Text(
        text = data.description,
        style = TextStyle(textDirection = TextDirection.Rtl),
        color = Color.White,
        fontSize = 14.sp,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun InfoText(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 14.sp)
}

@Composable
fun QualityItem(
    quality: Detail_page.Result.MovieUrl.Episode.Quality,
    onPlayClick: (url: String, srt: String?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(brightBlack)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = quality.qualityTitle,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { onPlayClick(quality.url, quality.srt) },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("پخش", color = Color.White)
        }
    }
}

@Composable
fun SeasonsTabs(
    seasons: List<Detail_page.Result.MovieUrl>,
    selectedIndex: Int,
    onSeasonSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = brightBlack,
        edgePadding = 12.dp
    ) {
        seasons.forEachIndexed { index, season ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onSeasonSelected(index) },
                text = {
                    Text(
                        text = season.title,
                        color = if (selectedIndex == index) Color.Red else Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}

@Composable
fun EpisodeItem(
    episode: Detail_page.Result.MovieUrl.Episode,
    onPlayClick: (url: String, srt: String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(brightBlack)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = "فصل ${episode.season} - ${episode.title}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        episode.quality.forEach { quality ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quality.qualityTitle,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Button(
                    onClick = { onPlayClick(quality.url, quality.srt) },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text("پخش", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TryNetworkDetail(onTryClick: () -> Unit, context: Context) {
    Toast.makeText(context, "لطفا اینترنت خود را متصل کنید.", Toast.LENGTH_LONG).show()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onTryClick() },
            modifier = Modifier.size(300.dp, 80.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray)
        ) {
            Text("تلاش مجدد", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
