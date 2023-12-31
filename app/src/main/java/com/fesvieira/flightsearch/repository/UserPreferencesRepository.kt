package com.fesvieira.flightsearch.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val LAST_SEARCH = stringPreferencesKey("last_search")
        val LAST_SELECTED = stringPreferencesKey("last_selected")
    }

    val lastSearch: Flow<String> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { preferences ->
            preferences[LAST_SEARCH] ?: ""
        }

    val lastSelected: Flow<String?> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { preferences ->
            preferences[LAST_SELECTED]
        }

    suspend fun saveLastSearch(string: String) {
        dataStore.edit { preferences ->
            preferences[LAST_SEARCH] = string
        }
    }

    suspend fun saveSelectedAirport(iata: String?) {
        dataStore.edit { preferences ->
            if (iata == null) preferences.minusAssign(LAST_SELECTED)
            else preferences[LAST_SELECTED] = iata
        }
    }
}