package com.example.smartharvest.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.smartharvest.data.responses.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): String {
        val preferences = runBlocking { dataStore.data.first() }
        return preferences[TOKEN_KEY] ?: ""
    }

    suspend fun login(user: LoginResult) {
        dataStore.edit { prefereces ->
            prefereces[NAME_KEY] = user.name
            prefereces[ID_KEY] = user.id
            prefereces[TOKEN_KEY] = user.token
            prefereces[TYPE_KEY] = user.type
        }
    }
    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun getUser(): Flow<LoginResult> {
        return dataStore.data.map { preferences ->
            LoginResult(
                preferences[NAME_KEY] ?: "",
                preferences[ID_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[TYPE_KEY] ?: ""
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val ID_KEY = stringPreferencesKey("id")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val TYPE_KEY = stringPreferencesKey("type")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}