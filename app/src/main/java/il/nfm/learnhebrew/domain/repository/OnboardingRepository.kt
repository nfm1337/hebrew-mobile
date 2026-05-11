package il.nfm.learnhebrew.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    val completed: Flow<Boolean>
    suspend fun markCompleted()
}
