package com.dev.divig.loginpageexample.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.dev.divig.loginpageexample.R
import com.dev.divig.loginpageexample.data.preference.UserPreference
import com.dev.divig.loginpageexample.ui.homepage.HomeActivity
import com.dev.divig.loginpageexample.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setSplashTime()
    }

    private fun setSplashTime() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                if (UserPreference(this@SplashScreenActivity).isUserLoggedIn) {
                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    startActivity(intent)
                } else {
                    val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    startActivity(intent)
                }
            }
        }
        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}