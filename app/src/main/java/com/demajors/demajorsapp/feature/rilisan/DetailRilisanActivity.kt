package com.demajors.demajorsapp.feature.rilisan

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityDetailRilisanBinding

class DetailRilisanActivity : BaseActivity<RilisanViewModel>() {

    override val viewModelClass: Class<RilisanViewModel> = RilisanViewModel::class.java
    private lateinit var binding: ActivityDetailRilisanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRilisanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }
    }
}
