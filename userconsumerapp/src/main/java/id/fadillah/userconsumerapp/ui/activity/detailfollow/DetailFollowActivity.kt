package id.fadillah.userconsumerapp.ui.activity.detailfollow

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import id.fadillah.userconsumerapp.R
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.databinding.ActivityDetailFollowBinding
import id.fadillah.userconsumerapp.ui.adapter.SectionsDetailPagerAdapter

class DetailFollowActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.plurals.followers_tab_title,
            R.plurals.following_tab_title,
            R.plurals.repository_tab_title
        )

        const val EXTRA_DETAIL_FOLLOW = "extra_Detail_follow"
        const val EXTRA_POSITION = "extra_position"
    }

    private lateinit var binding: ActivityDetailFollowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent?.getParcelableExtra<UserEntity>(EXTRA_DETAIL_FOLLOW)
        val position = intent?.getIntExtra(EXTRA_POSITION, 0)
        val sectionsDetailPagerAdapter = SectionsDetailPagerAdapter(this, data ?: UserEntity())
        val listDataFollow =
            arrayListOf(data?.followers ?: 0, data?.following ?: 0, data?.repository ?: 0)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = data?.name
        }

        binding.vp2DetailFollow.adapter = sectionsDetailPagerAdapter
        TabLayoutMediator(binding.tabsFollow, binding.vp2DetailFollow) { tabs, position ->
            tabs.text =
                when (position) {
                    0 -> resources.getQuantityString(
                        TAB_TITLES[position],
                        listDataFollow[0],
                        listDataFollow[0]
                    )
                    1 -> resources.getQuantityString(
                        TAB_TITLES[position],
                        listDataFollow[1],
                        listDataFollow[1]
                    )
                    2 -> resources.getQuantityString(
                        TAB_TITLES[position],
                        listDataFollow[2],
                        listDataFollow[2]
                    )
                    else -> resources.getQuantityString(
                        TAB_TITLES[position],
                        listDataFollow[0],
                        listDataFollow[0]
                    )
                }
        }.attach()

        if (position != null) {
            binding.vp2DetailFollow.currentItem = position
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