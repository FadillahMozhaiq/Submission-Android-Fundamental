package id.fadillah.fundamentalsubmission.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.databinding.ActivityMainBinding
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}