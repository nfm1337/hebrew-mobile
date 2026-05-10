package il.nfm.learnhebrew.domain.repository

import il.nfm.learnhebrew.data.model.request.GenerateSessionRequest
import il.nfm.learnhebrew.data.model.response.GenerateSessionResponse
import il.nfm.learnhebrew.data.network.NetworkResult

interface SessionRepository {
    suspend fun generateSession(request: GenerateSessionRequest): NetworkResult<GenerateSessionResponse>
}