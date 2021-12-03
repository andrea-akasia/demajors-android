package com.demajors.demajorsapp.feature.profile.mynft

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityMyNftBinding
import com.demajors.demajorsapp.feature.profile.mynft.opened.OpenedMyNFTFragment
import com.demajors.demajorsapp.feature.profile.mynft.unopened.UnopenedMyNFTFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyNftActivity : BaseActivity<MyNFTViewModel>(), HasAndroidInjector {

    override val viewModelClass: Class<MyNFTViewModel> = MyNFTViewModel::class.java
    private lateinit var binding: ActivityMyNftBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var tabAdapter: MyNFTTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyNftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        setupTabs()
    }

    private fun setupTabs() {
        tabAdapter = MyNFTTabAdapter(supportFragmentManager, lifecycle)
        tabAdapter.addFragments(UnopenedMyNFTFragment.newInstance(""))
        tabAdapter.addFragments(OpenedMyNFTFragment.newInstance(""))
        binding.pager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0) {
                tab.text = "Unopened"
            } else if (position == 1) {
                tab.text = "Opened"
            }
        }.attach()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
