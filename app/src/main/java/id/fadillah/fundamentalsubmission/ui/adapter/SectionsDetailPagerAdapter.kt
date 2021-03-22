package id.fadillah.fundamentalsubmission.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.fadillah.fundamentalsubmission.ui.fragment.followers.FollowersFragment
import id.fadillah.fundamentalsubmission.ui.fragment.following.FollowingFragment
import id.fadillah.fundamentalsubmission.ui.fragment.repository.RepositoryFragment

class SectionsDetailPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FollowersFragment()
        1 -> FollowingFragment()
        2 -> RepositoryFragment()
        else -> Fragment()
    }
}