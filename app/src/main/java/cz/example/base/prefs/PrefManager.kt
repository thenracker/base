package cz.example.base.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import cz.example.base.common.extensions.booleanPreference
import cz.example.base.common.extensions.stringPreference
import org.koin.core.KoinComponent

class PrefManager(context: Context) : KoinComponent {

    companion object {
        private const val PreferencesName = "My.prefs"

        // Keys
        private const val PrefUsername = "$PreferencesName.username"
    }

    val shared: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var username by stringPreference(PrefUsername)

    val isItGood by booleanPreference("IsItGood", true)


}