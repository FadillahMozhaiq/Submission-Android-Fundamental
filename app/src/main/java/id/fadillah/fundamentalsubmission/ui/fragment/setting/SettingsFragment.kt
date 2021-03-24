package id.fadillah.fundamentalsubmission.ui.fragment.setting

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.ui.activity.splash.SplashActivity
import id.fadillah.fundamentalsubmission.util.LanguageHelper


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        const val KEY_LANGUAGE = "language"
        const val KEY_REMINDER = "reminder"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
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
            if (setReminder == true)
                Toast.makeText(context, "Not yet implemented!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restartApp(activity: Activity) {
        val mStartActivity = Intent(activity, SplashActivity::class.java)
        activity.startActivity(mStartActivity)
        activity.finish()
    }
}