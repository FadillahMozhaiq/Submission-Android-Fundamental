package id.fadillah.fundamentalsubmission.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailActivity
import id.fadillah.fundamentalsubmission.ui.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, DetailActivity::class.java))
            finish()
        }, 2000L)
    }
}