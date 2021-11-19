package com.demajors.demajorsapp.feature.merchandise

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityDetailMerchandiseBinding

class DetailMerchandiseActivity : BaseActivity<MerchandiseViewModel>() {

    override val viewModelClass: Class<MerchandiseViewModel> = MerchandiseViewModel::class.java
    private lateinit var binding: ActivityDetailMerchandiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMerchandiseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        binding.sizeSmall.setOnClickListener {
            binding.sizeSmall.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option_red)
            binding.sizeMedium.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeDoubleExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
        }

        binding.sizeMedium.setOnClickListener {
            binding.sizeSmall.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeMedium.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option_red)
            binding.sizeLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeDoubleExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
        }

        binding.sizeLarge.setOnClickListener {
            binding.sizeSmall.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeMedium.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeLarge.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option_red)
            binding.sizeExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeDoubleExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
        }

        binding.sizeExtraLarge.setOnClickListener {
            binding.sizeSmall.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeMedium.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option_red)
            binding.sizeDoubleExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
        }

        binding.sizeDoubleExtraLarge.setOnClickListener {
            binding.sizeSmall.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeMedium.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_unselected_option)
            binding.sizeDoubleExtraLarge.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option_red)
        }
    }
}
