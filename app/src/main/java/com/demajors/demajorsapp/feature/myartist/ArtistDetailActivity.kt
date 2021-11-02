package com.demajors.demajorsapp.feature.myartist

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityArtistDetailBinding
import com.demajors.demajorsapp.feature.myartist.nft.ListArtistNFTFragment
import com.demajors.demajorsapp.feature.myartist.rilisan.ListRilisanFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ArtistDetailActivity : BaseActivity<ArtistViewModel>(), HasAndroidInjector {

    override val viewModelClass: Class<ArtistViewModel> = ArtistViewModel::class.java
    private lateinit var binding: ActivityArtistDetailBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        setupTabs()
    }

    private fun setupTabs() {
        tabAdapter = TabAdapter(supportFragmentManager, lifecycle)
        tabAdapter.addFragments(ListRilisanFragment.newInstance(""))
        tabAdapter.addFragments(ListArtistNFTFragment.newInstance(""))
        binding.pager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0) {
                tab.text = "RILISAN"
            } else {
                tab.text = "NFT"
            }
        }.attach()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
