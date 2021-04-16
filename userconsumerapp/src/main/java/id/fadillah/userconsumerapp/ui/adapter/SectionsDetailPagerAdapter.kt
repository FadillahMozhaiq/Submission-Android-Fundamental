package id.fadillah.userconsumerapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.ui.fragment.followers.FollowersFragment
import id.fadillah.userconsumerapp.ui.fragment.following.FollowingFragment
import id.fadillah.userconsumerapp.ui.fragment.repository.RepositoryFragment

class SectionsDetailPagerAdapter(fa: FragmentActivity, private val user: UserEntity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FollowersFragment().apply {
            setUserFollowers(user)
        }
        1 -> FollowingFragment().apply {
            setUserFollowing(user)
        }
        2 -> RepositoryFragment().apply {
            setUserData(user)
        }
        else -> Fragment()
    }
}