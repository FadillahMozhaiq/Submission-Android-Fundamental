package id.fadillah.fundamentalsubmission.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.fadillah.fundamentalsubmission.ui.fragment.favorite.FavoriteFragment
import id.fadillah.fundamentalsubmission.ui.fragment.home.HomeFragment
import id.fadillah.fundamentalsubmission.ui.fragment.setting.SettingsFragment

class SectionsMainPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> HomeFragment()
        1 -> FavoriteFragment()
        2 -> SettingsFragment()
        else -> Fragment()
    }
}