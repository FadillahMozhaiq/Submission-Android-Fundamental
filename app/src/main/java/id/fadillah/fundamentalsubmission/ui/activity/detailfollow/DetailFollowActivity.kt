package id.fadillah.fundamentalsubmission.ui.activity.detailfollow

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.databinding.ActivityDetailFollowBinding
import id.fadillah.fundamentalsubmission.ui.adapter.SectionsDetailPagerAdapter

class DetailFollowActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers_tab_title,
            R.string.following_tab_title,
            R.string.repository_tab_title
        )

        const val EXTRA_DETAIL_FOLLOW = "extra_Detail_follow"
    }

    private lateinit var binding: ActivityDetailFollowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent?.getParcelableExtra<UserEntity>(EXTRA_DETAIL_FOLLOW)
        val sectionsDetailPagerAdapter = SectionsDetailPagerAdapter(this)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = data?.name
        }

        binding.vp2DetailFollow.adapter = sectionsDetailPagerAdapter
        TabLayoutMediator(binding.tabsFollow, binding.vp2DetailFollow) { tabs, position ->
            tabs.text =
                when (position) {
                    0 -> getString(TAB_TITLES[position], data?.followers ?: 0)
                    1 -> getString(TAB_TITLES[position], data?.followers ?: 0)
                    2 -> getString(TAB_TITLES[position], data?.repository ?: 0)
                    else -> getString(TAB_TITLES[position], data?.followers ?: 0 )
                }
        }.attach()
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