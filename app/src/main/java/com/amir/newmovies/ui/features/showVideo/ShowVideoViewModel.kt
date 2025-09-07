// ShowVideoViewModel.kt
package com.amir.newmovies.ui.features.showVideo

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.media3.common.C
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer

class ShowVideoViewModel(private val context: Context) : ViewModel() {

    val videoUrl = mutableStateOf("")
    val subtitleUrl = mutableStateOf<String?>(null)

    val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    var currentPosition: Long = 0L
    var isPrepared: Boolean = false

    fun setVideo(url: String, srt: String?) {
        videoUrl.value = url
        subtitleUrl.value = srt
        isPrepared = false
    }

    fun preparePlayer() {
        val player = exoPlayer
        if (!isPrepared && videoUrl.value.isNotEmpty()) {
            val mediaItemBuilder = androidx.media3.common.MediaItem.Builder().setUri(videoUrl.value)
            subtitleUrl.value?.takeIf { it.isNotEmpty() }?.let {
                val subtitle = androidx.media3.common.MediaItem.SubtitleConfiguration.Builder(it.toUri())
                    .setMimeType(MimeTypes.APPLICATION_SUBRIP)
                    .setLanguage("fa")
                    .setSelectionFlags(C.SELECTION_FLAG_DEFAULT)
                    .build()
                mediaItemBuilder.setSubtitleConfigurations(listOf(subtitle))
            }
            player.setMediaItem(mediaItemBuilder.build())
            isPrepared = true
        }
        player.seekTo(currentPosition)
        player.playWhenReady = true
        player.prepare()
    }

    fun savePosition() {
        currentPosition = exoPlayer.currentPosition
    }

    fun releasePlayer() {
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
        isPrepared = false
        currentPosition = 0L
    }

    override fun onCleared() {
        exoPlayer.release()
        super.onCleared()
    }
}
