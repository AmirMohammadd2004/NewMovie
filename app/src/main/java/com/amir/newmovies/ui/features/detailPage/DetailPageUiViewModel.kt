package com.amir.newmovies.ui.features.detailPage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.amir.newmovies.model.data.Detail_page
import com.amir.newmovies.model.repository.detailRepository.DetailRepository
import com.amir.newmovies.ui.features.showVideo.ShowVideoViewModel
import com.amir.newmovies.util.emptyInfo
import kotlinx.coroutines.launch

class DetailPageUiViewModel(
    private val detailRepository: DetailRepository,
    val exoPlayer: ExoPlayer,
    private val showVideoViewModel: ShowVideoViewModel
) : ViewModel() {

    val movieInfo = mutableStateOf<Detail_page.Result.Info>(emptyInfo())
    val movieUrl = mutableStateOf<List<Detail_page.Result.MovieUrl>>(emptyList())

    val isPlaying = mutableStateOf(false)
    val isFullScreen = mutableStateOf(false)

    val currentUrl = mutableStateOf("")
    val currentSrt = mutableStateOf<String?>(null)
    var currentPosition: Long = 0L
    var selectedSeason: Int = 0

    // پاکسازی داده‌ها و توقف پلیر
    fun clearData() {
        stopPlayer()
        movieInfo.value = emptyInfo()
        movieUrl.value = emptyList()
    }

    // دریافت داده‌های فیلم
    fun getMovieData(idMovie: Int) {
        viewModelScope.launch {
            try {
                val data = detailRepository.getDataMovie(id = idMovie, emptyList = {})
                movieInfo.value = data.result.info
                movieUrl.value = data.result.movieUrl
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // پخش ویدیو در ShowVideoViewModel
    fun playMedia(videoUrl: String, subtitleUrl: String? = null, fullScreen: Boolean = true) {
        currentUrl.value = videoUrl
        currentSrt.value = subtitleUrl
        // ذخیره موقعیت فعلی قبل از ارسال
        currentPosition = exoPlayer.currentPosition

        // ارسال اطلاعات به ShowVideoViewModel و آماده سازی پلیر
        showVideoViewModel.setVideo(videoUrl, subtitleUrl)
        showVideoViewModel.currentPosition = currentPosition
        showVideoViewModel.preparePlayer()

        isFullScreen.value = fullScreen
        isPlaying.value = true
    }

    // توقف پلیر و پاکسازی منابع
    fun stopPlayer() {
        if (exoPlayer.isPlaying) exoPlayer.stop()
        exoPlayer.clearMediaItems()
        isPlaying.value = false
        isFullScreen.value = false
        currentUrl.value = ""
        currentSrt.value = null
        currentPosition = 0L
    }

    // ذخیره موقعیت فعلی ویدیو
    fun savePosition() {
        currentPosition = exoPlayer.currentPosition
    }

    override fun onCleared() {
        exoPlayer.stop()
        exoPlayer.release()
        super.onCleared()
    }
}
