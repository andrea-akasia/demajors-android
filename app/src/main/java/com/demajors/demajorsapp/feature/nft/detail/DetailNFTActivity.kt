package com.demajors.demajorsapp.feature.nft.detail

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityDetailNftBinding

class DetailNFTActivity : BaseActivity<DetailNFTViewModel>() {

    override val viewModelClass: Class<DetailNFTViewModel> = DetailNFTViewModel::class.java
    private lateinit var binding: ActivityDetailNftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

    }
}