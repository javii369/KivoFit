package com.KivoFit.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "kivofit_session")

@Singleton
class TokenStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val keyToken = stringPreferencesKey("auth_token")
    private val keyRole = stringPreferencesKey("user_role")
    private val keyEmail = stringPreferencesKey("user_email")
    private val keyName = stringPreferencesKey("user_name")

    val token: Flow<String?> = context.dataStore.data.map { it[keyToken] }
    val role: Flow<String?> = context.dataStore.data.map { it[keyRole] }
    val email: Flow<String?> = context.dataStore.data.map { it[keyEmail] }
    val name: Flow<String?> = context.dataStore.data.map { it[keyName] }

    suspend fun currentToken(): String? = context.dataStore.data.first()[keyToken]

    suspend fun save(token: String, role: String, email: String, name: String) {
        context.dataStore.edit {
            it[keyToken] = token
            it[keyRole] = role
            it[keyEmail] = email
            it[keyName] = name
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
