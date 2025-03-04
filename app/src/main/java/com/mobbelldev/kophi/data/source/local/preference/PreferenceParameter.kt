package com.mobbelldev.kophi.data.source.local.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceParameter {
    val IS_ONBOARDING = booleanPreferencesKey("is_onboarding")
    val IS_AUTHENTICATION = booleanPreferencesKey("is_authentication")
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    val LANGUAGE = stringPreferencesKey("language")
    val TOKEN = stringPreferencesKey("token")
    val US_ID = intPreferencesKey("us_id")
    val EMAIL = stringPreferencesKey("email")
}