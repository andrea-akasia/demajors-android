package com.demajors.demajorsapp.feature.song

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivitySongDetailBinding
import com.demajors.demajorsapp.feature.home.adapter.RecommendationAdapter
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class DetailSongActivity : BaseActivity<SongViewModel>() {

    override val viewModelClass: Class<SongViewModel> = SongViewModel::class.java
    private lateinit var binding: ActivitySongDetailBinding

    lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        binding.rvTracks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTracks.adapter = RecommendationAdapter(viewModel.getDummyHomeItems())

        setupPlayer()
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    override fun onPause() {
        if (player.isPlaying) { player.pause() }
        super.onPause()
    }

    fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/nft-demajors.appspot.com/o/test.mp3?alt=media&token=72f093fb-d328-4c1e-ba4e-2d4fa4b9ceb3")
        player.setMediaItem(mediaItem)
        player.prepare()

        binding.btnPreview.setOnClickListener {
            if (viewModel.isPLaying) {
                viewModel.isPLaying = false
                binding.imgPlayState.setImageResource(R.drawable.ic_play_white)
                binding.valuePlayState.text = "Preview"
                player.pause()
            } else {
                viewModel.isPLaying = true
                binding.imgPlayState.setImageResource(R.drawable.ic_pause)
                binding.valuePlayState.text = "Stop"
                player.seekTo(0)
                player.play()
            }
        }
    }
}
