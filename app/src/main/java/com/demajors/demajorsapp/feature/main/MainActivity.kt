package com.demajors.demajorsapp.feature.main

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
import com.demajors.demajorsapp.R


class MainActivity : BaseActivity<MainViewModel>(), HasAndroidInjector {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
                }
                R.id.nav_my_artist -> {
                }
                R.id.nav_cart -> {
                }
                R.id.nav_wallet -> {
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment()).commit()
                }
            }
            true
        }

        // default fragment
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
