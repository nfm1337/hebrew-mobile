package il.nfm.learnhebrew.domain.entity

data class Session(
    val generatedText: String,
    val targetWords: List<String>,
    val translation: String
)
