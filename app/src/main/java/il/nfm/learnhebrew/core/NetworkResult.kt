package il.nfm.learnhebrew.core

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int?, val message: String) : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
}

inline fun <T, R> NetworkResult<T>.map(transform: (T) -> (R)): NetworkResult<R> = when (this) {
    is NetworkResult.Error -> this
    NetworkResult.Loading -> NetworkResult.Loading
    is NetworkResult.Success -> NetworkResult.Success(transform(data))
}
