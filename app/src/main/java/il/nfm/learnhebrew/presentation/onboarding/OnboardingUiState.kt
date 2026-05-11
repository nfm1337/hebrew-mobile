package il.nfm.learnhebrew.presentation.onboarding

import il.nfm.learnhebrew.presentation.common.Level
import il.nfm.learnhebrew.presentation.common.Topic
import il.nfm.learnhebrew.presentation.common.defaultTopics

data class OnboardingUiState(
    val selectedLevel: Level = Level.A2,
    val topics: List<Topic> = emptyList(),
    val isLoading: Boolean = false,
)

const val MIN_TOPICS_REQUIRED = 3
val OnboardingUiState.canProceed: Boolean
    get() = topics.count { it.isChosen } >= MIN_TOPICS_REQUIRED

fun OnboardingUiState.withDefaults() = copy(topics = defaultTopics())
