package id.fadillah.fundamentalsubmission.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.codeboy.pager2_transformers.Pager2_CubeOutTransformer
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.databinding.ActivityMainBinding
import id.fadillah.fundamentalsubmission.ui.adapter.SectionsMainPagerAdapter
import id.fadillah.fundamentalsubmission.ui.fragment.timepicker.TimePickerFragment
import id.fadillah.fundamentalsubmission.util.broadcastreceiver.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

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

    override fun onDialogTimeSet(hourOfDay: Int, minute: Int) {
        val alarmReceiver = AlarmReceiver()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val date = dateFormat.format(calendar.time)
        alarmReceiver.setRepeatingAlarm(this, date)
    }
}