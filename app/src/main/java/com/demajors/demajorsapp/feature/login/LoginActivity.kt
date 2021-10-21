package com.demajors.demajorsapp.feature.login

import android.os.Bundle
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<LoginViewModel>() {

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
