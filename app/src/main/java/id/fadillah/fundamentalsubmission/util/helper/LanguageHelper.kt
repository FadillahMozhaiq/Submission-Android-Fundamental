package id.fadillah.fundamentalsubmission.util.helper

import android.app.Activity
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
        }
    }
}