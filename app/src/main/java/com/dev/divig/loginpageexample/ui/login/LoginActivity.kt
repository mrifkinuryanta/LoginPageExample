package com.dev.divig.loginpageexample.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.divig.loginpageexample.R
import com.dev.divig.loginpageexample.data.preference.UserPreference
import com.dev.divig.loginpageexample.databinding.ActivityLoginBinding
import com.dev.divig.loginpageexample.ui.homepage.HomeActivity
import com.dev.divig.loginpageexample.utils.Constant
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnLoginAccount.setOnClickListener {
            if (isFormLoginValid()) {
                checkLogin()
            }
        }
        binding.llAccountSsoFb.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_fb_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                false
            ).show()
        }
        binding.llActionSsoGmail.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_gmail_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                false
            ).show()
        }
    }

    private fun isFormLoginValid(): Boolean {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        if (username.isEmpty()) {
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.text_error_username_empty)
        } else {
            binding.tilUsername.isErrorEnabled = false
        }

        if (password.isEmpty()) {
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.text_error_password_empty)
        } else {
            binding.tilPassword.isErrorEnabled = false
        }

        return isFormValid
    }

    private fun checkLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (username == Constant.DUMMY_USERNAME && password == Constant.DUMMY_PASSWORD) {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                false
            ).show()
            saveLoginData()
            navigateToHomepage()
        } else {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_error),
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,
                false
            ).show()
        }
    }

    private fun navigateToHomepage() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    private fun saveLoginData() {
        UserPreference(this).isUserLoggedIn = true
    }
}