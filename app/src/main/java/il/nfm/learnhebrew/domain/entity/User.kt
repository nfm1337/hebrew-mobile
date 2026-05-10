package il.nfm.learnhebrew.domain.entity

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class User(
    val level: String,
    val topics: List<String>,
    val createdAt: Instant
)
