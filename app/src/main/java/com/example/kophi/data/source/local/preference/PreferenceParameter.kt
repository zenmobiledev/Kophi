package com.example.kophi.data.source.local.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceParameter {
    val IS_ONBOARDING = booleanPreferencesKey("is_onboarding")
    val IS_AUTHENTICATION = booleanPreferencesKey("is_authentication")
    val NAME = stringPreferencesKey("name")
}