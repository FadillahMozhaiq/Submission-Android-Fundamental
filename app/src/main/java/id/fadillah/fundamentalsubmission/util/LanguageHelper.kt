package id.fadillah.fundamentalsubmission.util

import android.app.Activity
import android.widget.Toast
import java.util.*

object LanguageHelper {
    fun setLocale(activity: Activity, languageCode: String) {
        if (languageCode.isNotEmpty()) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val resources = activity.resources
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        } else
            Toast.makeText(activity, "Empty Language Code!", Toast.LENGTH_SHORT).show()
    }
}