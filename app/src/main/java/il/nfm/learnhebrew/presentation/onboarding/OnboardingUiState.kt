package il.nfm.learnhebrew.presentation.onboarding

data class OnboardingUiState(
    val selectedLevel: Level = Level.A2,
    val topics: List<Topic> = emptyList(),
    val isLoading: Boolean = false
)

const val MIN_TOPICS_REQUIRED = 3
val OnboardingUiState.canProceed: Boolean
    get() = topics.count { it.isChosen } >= MIN_TOPICS_REQUIRED

enum class Level(val label: String, val title: String, val description: String) {
    A1("A1", "Начальный",  "Простые предложения, настоящее время, базовые слова"),
    A2("A2", "Базовый",    "Прошедшее и будущее время, бытовые темы, ~1500 слов"),
    B1("B1", "Средний",    "Газетные тексты, биньяны, абстрактные понятия"),
}

data class Topic(
    val id: String,
    val label: String,
    val isChosen: Boolean = false,
)

fun defaultTopics(): List<Topic> = listOf(
    Topic("food",     "Еда и кухня"),
    Topic("history",  "История Израиля"),
    Topic("politics", "Политика"),
    Topic("tech",     "Технологии"),
    Topic("science",  "Наука"),
    Topic("culture",  "Культура и искусство"),
    Topic("travel",   "Путешествия"),
    Topic("family",   "Семья и дом"),
    Topic("nature",   "Природа"),
    Topic("sport",    "Спорт"),
)
