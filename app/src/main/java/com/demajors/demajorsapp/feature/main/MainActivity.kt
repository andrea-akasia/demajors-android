package com.demajors.demajorsapp.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityMainBinding
import com.demajors.demajorsapp.feature.home.HomeFragment
import com.demajors.demajorsapp.feature.profile.ProfileFragment
import javax.inject.Inject

import android.view.WindowManager
import android.widget.Toast
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.feature.login.LoginActivity
import com.demajors.demajorsapp.feature.myartist.MyArtistFragment
import com.demajors.demajorsapp.feature.nft.list.ListNftFragment

class MainActivity : BaseActivity<MainViewModel>(), HasAndroidInjector {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.isLoggedIn()) {
            viewModel.refreshToken()
        } else {
            startActivity(
                Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
            this.finish()
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
                }
                R.id.nav_my_artist -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, MyArtistFragment()).commit()
                }
                R.id.nav_cart -> {
                }
                R.id.nav_wallet -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ListNftFragment()).commit()
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment()).commit()
                }
            }
            true
        }

        // default fragment
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

        viewModel.warningMessage.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )

        viewModel.onAuthFailed.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                startActivity(
                    Intent(this, LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                this.finish()
            }
        )
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
