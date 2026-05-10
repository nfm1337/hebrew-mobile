package il.nfm.learnhebrew.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUserRequest(
    @property:Json(name = "level") val level: String,
    @property:Json(name = "topics") val topics: List<String>,
)
