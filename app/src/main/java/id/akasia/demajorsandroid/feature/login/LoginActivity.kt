package id.akasia.demajorsandroid.feature.login

import android.os.Bundle
import id.akasia.demajorsandroid.base.BaseActivity
import id.akasia.demajorsandroid.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<LoginViewModel>() {

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}