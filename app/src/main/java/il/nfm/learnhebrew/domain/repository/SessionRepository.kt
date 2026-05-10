package il.nfm.learnhebrew.domain.repository

import il.nfm.learnhebrew.core.NetworkResult
import il.nfm.learnhebrew.domain.entity.Session

interface SessionRepository {
    suspend fun generateSession(userId: String, level: String, topic: String): NetworkResult<Session>
}