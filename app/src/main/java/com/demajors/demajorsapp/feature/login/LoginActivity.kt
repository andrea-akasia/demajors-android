package com.demajors.demajorsapp.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityLoginBinding
import com.demajors.demajorsapp.feature.main.MainActivity
import com.demajors.demajorsapp.feature.signup.SignUpActivity

class LoginActivity : BaseActivity<LoginViewModel>() {

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        viewModel.warningMessage.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )

        viewModel.isLoading.observe(
            this,
            {
                if (it) {
                    binding.viewLoading.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.INVISIBLE
                } else {
                    binding.viewLoading.visibility = View.GONE
                    binding.btnLogin.visibility = View.VISIBLE
                }
            }
        )

        viewModel.isLoginSuccess.observe(
            this,
            {
                startActivity(
                    Intent(this@LoginActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                this.finish()
            }
        )

        binding.actionRegister.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }

        binding.btnLogin.setOnClickListener {
            if (binding.valueEmail.text.toString().isNotEmpty() &&
                binding.valuePassword.text.toString().isNotEmpty()
            ) {
                viewModel.loginEmail(
                    binding.valueEmail.text.toString(),
                    binding.valuePassword.text.toString()
                )
            } else {
                Toast.makeText(this, "form belum lengkap", Toast.LENGTH_LONG).show()
            }
        }
    }
}
