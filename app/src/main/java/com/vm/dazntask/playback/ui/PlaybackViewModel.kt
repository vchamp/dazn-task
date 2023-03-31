package com.vm.dazntask.playback.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.vm.dazntask.app.navigation.DestinationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaybackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val player: Player
) : ViewModel() {

    private val videoUrl: String = savedStateHandle[DestinationArgs.VIDEO_URL] ?: ""

    init {
        with (player) {
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
            play()
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
