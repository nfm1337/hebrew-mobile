package il.nfm.learnhebrew.presentation.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingUiState(topics = defaultTopics()))
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun selectLevel(level: Level) {
        _uiState.update { it.copy(selectedLevel = level) }
    }

    fun toggleTopic(id: String) {
        _uiState.update { state ->
            state.copy(
                topics = state.topics.map { topic ->
                    if (topic.id == id) topic.copy(isChosen = !topic.isChosen) else topic
                }
            )
        }
    }
}
