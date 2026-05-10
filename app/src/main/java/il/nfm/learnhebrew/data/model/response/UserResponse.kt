package il.nfm.learnhebrew.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class UserResponse(
    @property:Json(name = "id") val id: String,
    @property:Json(name = "level") val level: String,
    @property:Json(name = "topics") val topics: List<String>,
    @property:Json(name = "created_at") val createdAt: Date
)
