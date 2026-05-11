package il.nfm.learnhebrew.data.repository

import il.nfm.learnhebrew.data.preferences.OnboardingPreferences
import il.nfm.learnhebrew.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val prefs: OnboardingPreferences,
) : OnboardingRepository {

    override val completed: Flow<Boolean> = prefs.completed
    override suspend fun markCompleted() = prefs.setCompleted()
}
