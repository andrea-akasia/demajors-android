package com.demajors.demajorsapp.feature.profile.mynft.unopened

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityDetailMynftUnopenedBinding
import com.demajors.demajorsapp.feature.profile.mynft.MyNFTViewModel

class UnopenedMyNFTDetailActivity : BaseActivity<MyNFTViewModel>(), Animator.AnimatorListener {

    override val viewModelClass: Class<MyNFTViewModel> = MyNFTViewModel::class.java
    private lateinit var binding: ActivityDetailMynftUnopenedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMynftUnopenedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        binding.viewAnimation.addAnimatorListener(this)

        binding.btnOpen.setOnClickListener {
            binding.nftImg.visibility = View.GONE
            binding.btnOpen.visibility = View.GONE
            binding.viewAnimation.visibility = View.VISIBLE
            binding.viewAnimation.playAnimation()
        }
    }

    override fun onAnimationStart(animator: Animator?) {}

    override fun onAnimationEnd(animator: Animator?) {
        binding.imgReveal.visibility = View.VISIBLE
        binding.viewAnimation.visibility = View.GONE
    }

    override fun onAnimationCancel(animator: Animator?) {}

    override fun onAnimationRepeat(animator: Animator?) {}
}
