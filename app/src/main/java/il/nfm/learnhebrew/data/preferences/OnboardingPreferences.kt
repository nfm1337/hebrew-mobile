package il.nfm.learnhebrew.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private val key = booleanPreferencesKey("onboarding_completed")

    val completed: Flow<Boolean> = dataStore.data.map { prefs -> prefs[key] ?: false }

    suspend fun setCompleted() {
        dataStore.edit { prefs -> prefs[key] = true }
    }
}
