package il.nfm.learnhebrew.core.util

import il.nfm.learnhebrew.core.NetworkResult
import retrofit2.Response

suspend fun <T> safeCall(call: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(response.code(), "Empty response body")
            }
        } else {
            NetworkResult.Error(response.code(), response.message())
        }
    } catch (e: Exception) {
        NetworkResult.Error(null, e.localizedMessage ?: "Unknown error")
    }
}
