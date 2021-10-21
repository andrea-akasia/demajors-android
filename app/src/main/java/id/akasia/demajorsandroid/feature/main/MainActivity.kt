package id.akasia.demajorsandroid.feature.main

import android.os.Bundle
import android.view.WindowInsets
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import id.akasia.demajorsandroid.base.BaseActivity
import id.akasia.demajorsandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    private lateinit var binding: ActivityMainBinding

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
    }
}
