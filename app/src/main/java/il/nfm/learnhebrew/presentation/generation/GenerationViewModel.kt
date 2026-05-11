package il.nfm.learnhebrew.presentation.generation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.nfm.learnhebrew.presentation.common.Level
import il.nfm.learnhebrew.presentation.common.defaultTopics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GenerationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        GenerationUiState(
            topics = defaultTopics(),
            selectedTopicId = "food",
        )
    )
    val uiState: StateFlow<GenerationUiState> = _uiState.asStateFlow()

    fun selectLevel(level: Level) {
        _uiState.update { it.copy(selectedLevel = level) }
    }

    fun selectTopic(id: String) {
        _uiState.update { it.copy(selectedTopicId = id) }
    }

    fun selectLength(length: TextLength) {
        _uiState.update { it.copy(selectedLength = length) }
    }

    fun setRecent(items: List<RecentTextItem>) {
        _uiState.update { it.copy(recent = items) }
    }

    fun generate() {
        _uiState.update { it.copy(isGenerating = true) }
    }
}
