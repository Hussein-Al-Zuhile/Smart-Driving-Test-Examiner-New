package com.tatweer.smartdrivingtest.presentation.base

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private companion object {
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    fun Context.userToken(): Flow<String?> = dataStore.data.map {
        it[USER_TOKEN]
    }

    suspend fun Context.editUserToken(token: String?) = dataStore.edit {
        it[USER_TOKEN]
    }
}