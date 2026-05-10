package il.nfm.learnhebrew.data.repository

import il.nfm.learnhebrew.core.util.safeCall
import il.nfm.learnhebrew.data.model.request.GenerateSessionRequest
import il.nfm.learnhebrew.data.model.response.GenerateSessionResponse
import il.nfm.learnhebrew.data.network.ApiService
import il.nfm.learnhebrew.data.network.NetworkResult
import il.nfm.learnhebrew.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SessionRepository {

    override suspend fun generateSession(
        request: GenerateSessionRequest
    ): NetworkResult<GenerateSessionResponse> = safeCall { api.generateSession(request) }
}
