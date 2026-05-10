package il.nfm.learnhebrew.data.repository

import il.nfm.learnhebrew.core.NetworkResult
import il.nfm.learnhebrew.core.map
import il.nfm.learnhebrew.core.util.safeCall
import il.nfm.learnhebrew.data.model.request.CreateUserRequest
import il.nfm.learnhebrew.data.network.ApiService
import il.nfm.learnhebrew.data.toDomain
import il.nfm.learnhebrew.domain.entity.User
import il.nfm.learnhebrew.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override suspend fun register(level: String, topics: List<String>): NetworkResult<User> {
        val request = CreateUserRequest(level = level, topics = topics)
        return safeCall { api.createUser(request) }.map { it.toDomain() }
    }
}
