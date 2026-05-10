package il.nfm.learnhebrew.domain.repository

import il.nfm.learnhebrew.core.NetworkResult
import il.nfm.learnhebrew.domain.entity.User

interface UserRepository {

    suspend fun register(level: String, topics: List<String>): NetworkResult<User>
}
