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
import id.fadillah.fundamentalsubmission.ui.activity.detailfollow.DetailFollowActivity
import id.fadillah.fundamentalsubmission.ui.activity.detailfollow.DetailFollowActivity.Companion.EXTRA_DETAIL_FOLLOW
import id.fadillah.fundamentalsubmission.ui.activity.detailfollow.DetailFollowActivity.Companion.EXTRA_POSITION
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
    private lateinit var dataDetail: UserEntity
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val data = intent?.getParcelableExtra<UserEntity>(EXTRA_DETAIL_DATA)

        data?.bookmarked?.let {
            if (it) {
                isFavorite = it
                setFabFavorite(isFavorite)
            } else {
                data.username?.let { username ->
                    viewModel.isFavoriteUser(username).observe(this, { favorite ->
                        isFavorite = favorite
                        setFabFavorite(isFavorite)
                    })
                } ?: run {
                    isFavorite = false
                    setFabFavorite(isFavorite)
                }
            }
        }
        data?.image?.let { url -> ImageHelper.getImage(binding.ivDetail, url) }
        viewModel.getDetailUser(data?.username ?: "").observe(this, {
//            App bar layout
            binding.collapsingToolbar.subtitle = it.company
            binding.collapsingToolbar.title = if (it.name.isNullOrEmpty()) "Unknown" else it.name
//            Content
            binding.content.tvUsername.text = it.username
            binding.content.tvRepository.text = resources.getQuantityString(
                R.plurals.count_repository,
                it.repository,
                it.repository
            )
            binding.content.tvFollowers.text = it.followers.toString()
            binding.content.tvFollowing.text = it.following.toString()
            binding.content.tvBio.text = it.bio ?: getString(R.string.long_lorem)
            if (it.location == null)
                binding.content.cvLocation.visibility = View.GONE
            else {
                binding.content.tvLocation.text = it.location
            }
            dataDetail = it
        })

        binding.fabFavorite.setOnClickListener {
//            Change the state
            isFavorite = !isFavorite

            setFabFavorite(isFavorite)

            data?.let {
                if (!isFavorite) {
                    Toast.makeText(this, getString(R.string.success_delete), Toast.LENGTH_SHORT)
                        .show()
                } else
                    Toast.makeText(this, getString(R.string.success_add), Toast.LENGTH_SHORT).show()

                it.bookmarked = !isFavorite
                viewModel.setFavoriteUser(it)
            } ?: run {
                Toast.makeText(this, getString(R.string.failed_proceed), Toast.LENGTH_SHORT).show()
            }
        }

        binding.fabShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.follow_my_github_account, data?.username)
                )
            }
            startActivity(Intent.createChooser(intent, getString(R.string.share_your_profile_now)))
        }

        binding.ivDetail.setOnClickListener {
            startActivity(Intent(this, ImageViewerActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_IMAGE, data?.image ?: "")
            })
        }

        binding.content.cvFollow.setOnClickListener {
            startActivity(Intent(this, DetailFollowActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_FOLLOW, dataDetail)
                putExtra(EXTRA_POSITION, 0)
            })
        }

        binding.content.cvRepository.setOnClickListener {
            startActivity(Intent(this, DetailFollowActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_FOLLOW, dataDetail)
                putExtra(EXTRA_POSITION, 2)
            })
        }

        binding.content.cvFollowing.setOnClickListener {
            startActivity(Intent(this, DetailFollowActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_FOLLOW, dataDetail)
                putExtra(EXTRA_POSITION, 1)
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

    private fun setFabFavorite(isFavorite: Boolean) {
        if (isFavorite)
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
        else
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border)
    }
}