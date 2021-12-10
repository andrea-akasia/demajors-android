package com.demajors.demajorsapp.feature.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityConfirmEmailBinding
import com.demajors.demajorsapp.feature.main.MainActivity

class VerifyEmailActivity : BaseActivity<SignUpViewModel>() {

    override val viewModelClass: Class<SignUpViewModel> = SignUpViewModel::class.java
    private lateinit var binding: ActivityConfirmEmailBinding

    companion object {
        const val KEY_EMAIL = "EMAIL"
        const val KEY_PASS = "PASS"
        const val KEY_ACTION = "ACTION"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmEmailBinding.inflate(layoutInflater)
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
                } else {
                    binding.viewLoading.visibility = View.GONE
                }
            }
        )

        viewModel.onVerifySuccess.observe(
            this,
            {
                startActivity(
                    Intent(this@VerifyEmailActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                this.finish()
            }
        )

        binding.label.text = "Mohon masukan 6 digit kode verifikasi dikirim ke\n${intent.getStringExtra(KEY_EMAIL)}"

        binding.viewOtp.setOtpCompletionListener {
            if (intent.getStringExtra(KEY_ACTION) == "LOGIN") {
                viewModel.verifyLogin(
                    email = intent.getStringExtra(KEY_EMAIL)!!,
                    pass = intent.getStringExtra(KEY_PASS)!!,
                    otp = it
                )
            } else {
                viewModel.verify(
                    email = intent.getStringExtra(KEY_EMAIL)!!,
                    pass = intent.getStringExtra(KEY_PASS)!!,
                    otp = it
                )
            }
        }

        binding.actionResend.setOnClickListener {
            if (intent.getStringExtra(KEY_ACTION) == "LOGIN") {
                viewModel.loginEmail(
                    email = intent.getStringExtra(KEY_EMAIL)!!,
                    pass = intent.getStringExtra(KEY_PASS)!!
                )
            } else {
                viewModel.signup(
                    email = intent.getStringExtra(KEY_EMAIL)!!,
                    pass = intent.getStringExtra(KEY_PASS)!!
                )
            }
        }
    }
}
