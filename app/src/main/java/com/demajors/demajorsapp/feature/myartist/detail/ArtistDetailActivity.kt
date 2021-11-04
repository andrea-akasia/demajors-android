package com.demajors.demajorsapp.feature.myartist.detail

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityArtistDetailBinding
import com.demajors.demajorsapp.feature.myartist.detail.merchandise.ListArtistMerchandiseFragment
import com.demajors.demajorsapp.feature.myartist.detail.nft.ListArtistNFTFragment
import com.demajors.demajorsapp.feature.myartist.detail.rilisan.ListRilisanFragment
import com.google.android.material.appbar.AppBarLayout
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Efek Rumah Kaca"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var isShow = true
        var scrollRange = -1
        binding.appbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.collapseToolbar.title = "Efek Rumah Kaca"
                    isShow = true
                } else if (isShow) {
                    binding.collapseToolbar.title = " " // careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        )

        setupTabs()
    }

    private fun setupTabs() {
        tabAdapter = TabAdapter(supportFragmentManager, lifecycle)
        tabAdapter.addFragments(ListRilisanFragment.newInstance(""))
        tabAdapter.addFragments(ListArtistMerchandiseFragment.newInstance(""))
        tabAdapter.addFragments(ListArtistNFTFragment.newInstance(""))
        binding.pager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0) {
                tab.text = "RILISAN"
            } else if (position == 1) {
                tab.text = "MERCHANDISE"
            } else if (position == 2) {
                tab.text = "NFT"
            }
        }.attach()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
