package com.demajors.demajorsapp.feature.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<SignUpViewModel>() {

    override val viewModelClass: Class<SignUpViewModel> = SignUpViewModel::class.java
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        viewModel.warningMessage.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )

        viewModel.isLoading.observe(
            this,
            {
                if (it) {
                    binding.viewLoading.visibility = View.VISIBLE
                    binding.btnRegister.visibility = View.INVISIBLE
                } else {
                    binding.viewLoading.visibility = View.INVISIBLE
                    binding.btnRegister.visibility = View.VISIBLE
                }
            }
        )

        viewModel.onSignUpSuccess.observe(
            this,
            {
                startActivity(
                    Intent(
                        this@SignUpActivity,
                        VerifyEmailActivity::class.java
                    )
                        .putExtra(VerifyEmailActivity.KEY_ACTION, "REGISTER")
                        .putExtra(VerifyEmailActivity.KEY_EMAIL, binding.valueEmail.text.toString())
                        .putExtra(VerifyEmailActivity.KEY_PASS, binding.valuePassword.text.toString())
                )
            }
        )

        binding.btnRegister.setOnClickListener {
            if (isFormValid()) {
                viewModel.signup(
                    binding.valueEmail.text.toString(),
                    binding.valuePassword.text.toString()
                )
            } else {
                Toast.makeText(this, "form belum benar, silahkan cek kembali", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFormValid(): Boolean {
        var result = true
        if (binding.valueEmail.text.toString().isEmpty()) { result = false }
        if (binding.valuePassword.text.toString().isEmpty()) { result = false }
        if (binding.valueConfirmPassword.text.toString().isEmpty()) { result = false }
        if (binding.valuePassword.text.toString() != binding.valueConfirmPassword.text.toString()) { result = false }
        if (binding.valuePassword.text.toString().length < 8) { result = false }
        if (!isValidPassword(binding.valuePassword.text.toString())) { result = false }
        if (!binding.checkBox.isChecked) { result = false }

        return result
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                "(?=.*[0-9])" + // at least 1 digit
                "(?=.*[a-z])" + // at least 1 lower case letter
                "(?=.*[A-Z])" + // at least 1 upper case letter
                "(?=.*[a-zA-Z])" + // any letter
                "(?=\\S+$)" + // no white spaces
                ".{8,}" + // at least 8 characters
                "$"
        )

        return passwordREGEX.matcher(password).matches()
    }
}
