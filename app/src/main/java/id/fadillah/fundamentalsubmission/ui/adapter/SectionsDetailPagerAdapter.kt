package id.fadillah.fundamentalsubmission.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.ui.fragment.followers.FollowersFragment
import id.fadillah.fundamentalsubmission.ui.fragment.following.FollowingFragment
import id.fadillah.fundamentalsubmission.ui.fragment.repository.RepositoryFragment

class SectionsDetailPagerAdapter(fa: FragmentActivity, private val user: UserEntity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FollowersFragment().apply {
            userEntity = user
        }
        1 -> FollowingFragment().apply {
            userEntity = user
        }
        2 -> RepositoryFragment().apply {
            userEntity = user
        }
        else -> Fragment()
    }
}