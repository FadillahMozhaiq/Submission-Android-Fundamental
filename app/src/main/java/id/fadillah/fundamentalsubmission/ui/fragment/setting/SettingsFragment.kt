package id.fadillah.fundamentalsubmission.ui.fragment.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import id.fadillah.fundamentalsubmission.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}