package com.dev.divig.loginpageexample.ui.homepage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dev.divig.loginpageexample.R
import com.dev.divig.loginpageexample.data.preference.UserPreference
import com.dev.divig.loginpageexample.databinding.ActivityHomeBinding
import com.dev.divig.loginpageexample.ui.login.LoginActivity
import com.dev.divig.loginpageexample.utils.Constant
import com.dev.divig.loginpageexample.utils.ProtectedMediaChromeClient
import com.shashank.sony.fancytoastlib.FancyToast

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.text_actionbar_homepage)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.wvPage.settings.javaScriptEnabled = true
        binding.wvPage.settings.allowContentAccess = true
        binding.wvPage.settings.domStorageEnabled = true
        binding.wvPage.webChromeClient = ProtectedMediaChromeClient()
        binding.wvPage.loadUrl(Constant.URL_WEBVIEW_HOMEPAGE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                FancyToast.makeText(
                    this,
                    getString(R.string.text_toast_logout_success),
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    false
                ).show()
                deleteSessionData()
                navigateToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    private fun deleteSessionData() {
        UserPreference(this).isUserLoggedIn = false
    }
}