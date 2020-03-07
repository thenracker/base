package cz.example.base.common.extensions

import android.content.SharedPreferences
import cz.example.base.common.delegate.PreferenceProperty
import cz.example.base.prefs.PrefManager
import kotlin.properties.ReadWriteProperty

fun PrefManager.longPreference(
    key: String,
    defaultValue: Long = 0L
): ReadWriteProperty<Any, Long> =
    PreferenceProperty(
        sharedPreferences = this.shared,
        key = key,
        defaultValue = defaultValue,
        getter = SharedPreferences::getLong,
        setter = SharedPreferences.Editor::putLong
    )

fun PrefManager.intPreference(
    key: String,
    defaultValue: Int = 0
): ReadWriteProperty<Any, Int> =
    PreferenceProperty(
        sharedPreferences = this.shared,
        key = key,
        defaultValue = defaultValue,
        getter = SharedPreferences::getInt,
        setter = SharedPreferences.Editor::putInt
    )

fun PrefManager.booleanPreference(
    key: String,
    defaultValue: Boolean = false
): ReadWriteProperty<Any, Boolean> =
    PreferenceProperty(
        sharedPreferences = this.shared,
        key = key,
        defaultValue = defaultValue,
        getter = SharedPreferences::getBoolean,
        setter = SharedPreferences.Editor::putBoolean
    )

fun PrefManager.stringPreference(
    key: String,
    defaultValue: String? = null
): ReadWriteProperty<Any, String?> =
    PreferenceProperty(
        sharedPreferences = this.shared,
        key = key,
        defaultValue = defaultValue,
        getter = SharedPreferences::getString,
        setter = SharedPreferences.Editor::putString
    )