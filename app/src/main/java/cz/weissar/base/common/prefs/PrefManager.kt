package cz.weissar.base.common.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import cz.weissar.base.common.extensions.booleanPreference
import cz.weissar.base.common.extensions.stringPreference
import org.koin.core.KoinComponent

class PrefManager(context: Context) : KoinComponent {

    companion object {
        private const val PreferencesName = "My.prefs"

        // Keys
        private const val PrefUsername = "$PreferencesName.username"
        private const val PrefCredentials = "$PreferencesName.credentials"
    }

    val shared: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var username by stringPreference(PrefUsername)
    var credentials by stringPreference(PrefCredentials)

    val isItGood by booleanPreference("IsItGood", true)


}