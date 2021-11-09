package com.demajors.demajorsapp.feature.song

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivitySongDetailBinding
import com.demajors.demajorsapp.feature.home.adapter.RecommendationAdapter

class DetailSongActivity : BaseActivity<SongViewModel>() {

    override val viewModelClass: Class<SongViewModel> = SongViewModel::class.java
    private lateinit var binding: ActivitySongDetailBinding

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
    }
}