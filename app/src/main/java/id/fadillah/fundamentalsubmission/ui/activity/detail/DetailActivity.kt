
package id.fadillah.fundamentalsubmission.ui.activity.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.databinding.ActivityDetailBinding
import id.fadillah.fundamentalsubmission.ui.activity.imageviewer.ImageViewerActivity
import id.fadillah.fundamentalsubmission.ui.activity.imageviewer.ImageViewerActivity.Companion.EXTRA_DETAIL_IMAGE
import id.fadillah.fundamentalsubmission.util.ImageHelper
import id.fadillah.fundamentalsubmission.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL_DATA = "extra_Detail_Data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val data = intent?.getParcelableExtra<UserEntity>(EXTRA_DETAIL_DATA)

        data?.image?.let { url -> ImageHelper.getImage(binding.ivDetail, url) }
        viewModel.getDetailUser(data?.username ?: "").observe(this, {
//            App bar layout
            binding.collapsingToolbar.subtitle = it.company
            binding.collapsingToolbar.title = if (it.name.isNullOrEmpty()) "Unknown" else it.name
//            Content
            binding.content.tvUsername.text = it.username
            binding.content.tvRepository.text = getString(R.string.count_repository, it.repository)
            binding.content.tvFollowers.text = it.followers.toString()
            binding.content.tvFollowing.text = it.following.toString()
            binding.content.tvBio.text = it.bio ?: getString(R.string.long_lorem)
            if (it.location == null)
                binding.content.cvLocation.visibility = View.GONE
            else {
                binding.content.tvLocation.text = it.location
            }
        })

        binding.fabFavorite.setOnClickListener {
            Toast.makeText(this, "Not yet implemented!", Toast.LENGTH_SHORT).show()
        }

        binding.fabShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Hello! Follow my Github account with username: ${data?.username}")
            }
            startActivity(Intent.createChooser(intent, "Share your profile now!"))
        }

        binding.ivDetail.setOnClickListener {
            startActivity(Intent(this, ImageViewerActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_IMAGE, data?.image ?: "")
            })
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