package id.akasia.demajorsandroid.feature.main

import android.os.Bundle
import android.view.WindowInsets
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import id.akasia.demajorsandroid.R
import id.akasia.demajorsandroid.base.BaseActivity
import id.akasia.demajorsandroid.databinding.ActivityMainBinding
import id.akasia.demajorsandroid.feature.home.HomeFragment
import id.akasia.demajorsandroid.feature.profile.ProfileFragment
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), HasAndroidInjector {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) {
            val insetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
            insetsControllerCompat.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            insetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars())
        } else {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
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
