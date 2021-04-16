package id.fadillah.userconsumerapp.ui.fragment.setting

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import id.fadillah.userconsumerapp.R
import id.fadillah.userconsumerapp.ui.activity.splash.SplashActivity
import id.fadillah.userconsumerapp.ui.fragment.timepicker.TimePickerFragment
import id.fadillah.userconsumerapp.util.broadcastreceiver.AlarmReceiver
import id.fadillah.userconsumerapp.util.helper.LanguageHelper


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener,
    TimePickerFragment.DialogTimeCancelListener {

    companion object {
        const val KEY_LANGUAGE = "language"
        const val KEY_REMINDER = "reminder"
    }

    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        alarmReceiver = AlarmReceiver()
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals(KEY_LANGUAGE)) {
            val language = sharedPreferences?.getString(key, "")
            LanguageHelper.setLocale(requireActivity(), language ?: "")

//            Restart The App
            restartApp(requireActivity())
        } else if (key.equals(KEY_REMINDER)) {
            val setReminder = sharedPreferences?.getBoolean(key, false)
            if (setReminder == true) {
                TimePickerFragment().show(childFragmentManager, "TimePicker")
            } else {
                if (alarmReceiver.isAlarmSet(requireContext())) {
                    alarmReceiver.cancelAlarm(requireContext())
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.reminder_not_activated),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun restartApp(activity: Activity) {
        val mStartActivity = Intent(activity, SplashActivity::class.java)
        activity.startActivity(mStartActivity)
        activity.finish()
    }

    override fun onDialogCancel(canceled: Boolean) {
        if (canceled) {
            Toast.makeText(
                context,
                getString(R.string.reminder_not_activated),
                Toast.LENGTH_SHORT
            ).show()
            val reminderSwitch = findPreference<SwitchPreferenceCompat>("reminder")
            reminderSwitch?.switchTextOff
        }
    }
}