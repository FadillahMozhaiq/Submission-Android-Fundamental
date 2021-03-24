package id.fadillah.fundamentalsubmission.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.codeboy.pager2_transformers.Pager2_CubeOutTransformer
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.databinding.ActivityMainBinding
import id.fadillah.fundamentalsubmission.ui.adapter.SectionsMainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: SectionsMainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pagerAdapter = SectionsMainPagerAdapter(this)

        binding.vp2Home.apply {
            adapter = pagerAdapter
            setPageTransformer(Pager2_CubeOutTransformer())
        }

        binding.tabs.addBubbleListener { id ->
            when (id) {
                R.id.home_menu -> binding.vp2Home.setCurrentItem(0, false)
                R.id.favorite_menu -> binding.vp2Home.setCurrentItem(1, false)
                R.id.settings_menu -> binding.vp2Home.setCurrentItem(2, false)
            }
        }

        binding.vp2Home.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabs.setSelected(position)
            }
        })
    }
}