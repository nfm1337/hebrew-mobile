package il.nfm.learnhebrew.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerateSessionRequest(
    @property:Json(name = "user_id") val userId: String,
    @property:Json(name = "level") val level: String,
    @property:Json(name = "topic") val topic: String,
)
