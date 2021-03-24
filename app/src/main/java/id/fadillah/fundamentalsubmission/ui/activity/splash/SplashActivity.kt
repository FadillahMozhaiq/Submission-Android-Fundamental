package id.fadillah.fundamentalsubmission.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.ui.activity.main.MainActivity
import id.fadillah.fundamentalsubmission.ui.fragment.setting.SettingsFragment.Companion.KEY_LANGUAGE
import id.fadillah.fundamentalsubmission.util.LanguageHelper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        Load settings
        loadSettings()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000L)
    }

    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        val language = sp.getString(KEY_LANGUAGE, "")
        LanguageHelper.setLocale(this, language ?: "en")
    }
}