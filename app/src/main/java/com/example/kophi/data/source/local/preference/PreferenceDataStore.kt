package com.example.kophi.data.source.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "kophi_pref")

class PreferenceDataStore(private val dataStore: DataStore<Preferences>) {
    // Onboarding
    suspend fun setOnboarding(isOnboarding: Boolean) {
        dataStore.edit {
            it[PreferenceParameter.IS_ONBOARDING] = isOnboarding
        }
    }

    suspend fun getOnboarding(): Boolean {
        return dataStore.data.first()[PreferenceParameter.IS_ONBOARDING] ?: false
    }

    // Authentication
    suspend fun setAuthenticationUser(isAuthentication: Boolean) {
        dataStore.edit {
            it[PreferenceParameter.IS_AUTHENTICATION] = isAuthentication
        }
    }

    suspend fun getAuthenticationUser(): Boolean {
        return dataStore.data.first()[PreferenceParameter.IS_AUTHENTICATION] ?: false
    }

    // Profile
    suspend fun logout() {
        dataStore.edit { preferences ->
            listOf(
                PreferenceParameter.IS_AUTHENTICATION,
                PreferenceParameter.NAME
            ).forEach { preferences.remove(it) }
        }
    }
}