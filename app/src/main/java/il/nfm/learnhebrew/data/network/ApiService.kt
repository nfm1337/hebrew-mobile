package il.nfm.learnhebrew.data.network

import il.nfm.learnhebrew.data.model.request.CreateUserRequest
import il.nfm.learnhebrew.data.model.request.GenerateSessionRequest
import il.nfm.learnhebrew.data.model.response.GenerateSessionResponse
import il.nfm.learnhebrew.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("sessions/generate")
    suspend fun generateSession(
        @Body request: GenerateSessionRequest
    ): Response<GenerateSessionResponse>

    @POST("users/")
    suspend fun createUser(
        @Body request: CreateUserRequest
    ): Response<UserResponse>

    @GET("health")
    suspend fun health(): Response<Unit>
}