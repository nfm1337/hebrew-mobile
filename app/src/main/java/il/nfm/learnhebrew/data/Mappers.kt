package il.nfm.learnhebrew.data

import il.nfm.learnhebrew.data.model.response.GenerateSessionResponse
import il.nfm.learnhebrew.data.model.response.UserResponse
import il.nfm.learnhebrew.domain.entity.Session
import il.nfm.learnhebrew.domain.entity.User
import kotlin.time.ExperimentalTime

fun GenerateSessionResponse.toDomain() = Session(
    generatedText = generatedText,
    targetWords = targetWords,
    translation = translation,
)

@OptIn(ExperimentalTime::class)
fun UserResponse.toDomain() = User(
    level = level,
    topics = topics,
    createdAt = createdAt
)
