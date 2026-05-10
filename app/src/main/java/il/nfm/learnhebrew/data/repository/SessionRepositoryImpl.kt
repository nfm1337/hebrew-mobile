package il.nfm.learnhebrew.data.repository

import il.nfm.learnhebrew.core.NetworkResult
import il.nfm.learnhebrew.core.map
import il.nfm.learnhebrew.core.util.safeCall
import il.nfm.learnhebrew.data.model.request.GenerateSessionRequest
import il.nfm.learnhebrew.data.network.ApiService
import il.nfm.learnhebrew.data.toDomain
import il.nfm.learnhebrew.domain.entity.Session
import il.nfm.learnhebrew.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SessionRepository {

    override suspend fun generateSession(
        userId: String,
        level: String,
        topic: String
    ): NetworkResult<Session> {
        val request = GenerateSessionRequest(userId = userId, level = level, topic = topic)
        return safeCall { api.generateSession(request) }.map { it.toDomain() }
    }
}
