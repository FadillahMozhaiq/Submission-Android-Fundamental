package id.fadillah.fundamentalsubmission.ui.activity.imageviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fadillah.fundamentalsubmission.databinding.ActivityImageViewerBinding
import id.fadillah.fundamentalsubmission.util.ImageHelper

class ImageViewerActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL_IMAGE = "extra_detail_image"
    }
    private lateinit var binding: ActivityImageViewerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlImage = intent?.getStringExtra(EXTRA_DETAIL_IMAGE)

        if (urlImage != null) {
            ImageHelper.getImage(binding.pvDetail, urlImage)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}