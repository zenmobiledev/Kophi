package com.mobbelldev.kophi.data.source.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "kophi_pref")

class PreferenceDataStore(private val dataStore: DataStore<Preferences>) {
    // Onboarding
    suspend fun setOnboarding(isOnboarding: Boolean) {
        dataStore.edit {
            it[PreferenceParameter.IS_ONBOARDING] = isOnboarding
        }
    }

    suspend fun getOnboarding(): Boolean {
        return dataStore.data.first()[PreferenceParameter.IS_ONBOARDING] ?: true
    }

    // Authentication
    suspend fun saveToken(token: String) {
        dataStore.edit {
            it[PreferenceParameter.TOKEN] = token
        }
    }

    suspend fun getToken(): String {
        return dataStore.data.first()[PreferenceParameter.TOKEN] ?: ""
    }

    suspend fun saveUsId(usId: Int) {
        dataStore.edit {
            it[PreferenceParameter.US_ID] = usId
        }
    }

    suspend fun getUsId(): Int {
        return dataStore.data.first()[PreferenceParameter.US_ID] ?: 0
    }

    suspend fun setAuthenticationUser(isAuthentication: Boolean) {
        dataStore.edit {
            it[PreferenceParameter.IS_AUTHENTICATION] = isAuthentication
        }
    }

    suspend fun getAuthenticationUser(): Boolean {
        return dataStore.data.first()[PreferenceParameter.IS_AUTHENTICATION] ?: false
    }

    // Profile
    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[PreferenceParameter.IS_DARK_MODE] = isDarkMode
        }
    }

    fun getDarkMode(): Flow<Boolean> {
        return dataStore.data.map {
            it[PreferenceParameter.IS_DARK_MODE] ?: false
        }
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit {
            it[PreferenceParameter.LANGUAGE] = language
        }
    }

    fun getLanguage(): Flow<String> {
        return dataStore.data.map {
            it[PreferenceParameter.LANGUAGE] ?: "en"
        }
    }


    suspend fun logout() {
        dataStore.edit {
            it.remove(PreferenceParameter.TOKEN)
        }
    }
}