package il.nfm.learnhebrew.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerateSessionResponse(
    @property:Json(name = "session_id") val sessionId: String,
    @property:Json(name = "generated_text") val generatedText: String,
    @property:Json(name = "target_words") val targetWords: List<String>,
    @property:Json(name = "translation") val translation: String
)
