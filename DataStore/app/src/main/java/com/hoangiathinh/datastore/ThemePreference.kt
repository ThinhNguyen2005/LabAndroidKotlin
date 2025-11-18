package com.hoangiathinh.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreference(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_color")
    }

    // Lưu màu
    suspend fun saveTheme(color: String) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = color
        }
    }

    val themeColor: Flow<String> = dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: "White"  // mặc định là trắng
    }
}
