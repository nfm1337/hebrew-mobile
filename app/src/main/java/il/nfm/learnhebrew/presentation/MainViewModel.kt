package il.nfm.learnhebrew.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import il.nfm.learnhebrew.domain.repository.OnboardingRepository
import il.nfm.learnhebrew.presentation.navigation.Main
import il.nfm.learnhebrew.presentation.navigation.Onboarding
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onboardingRepo: OnboardingRepository,
) : ViewModel() {

    sealed interface StartState {
        data object Loading : StartState
        data class Start(val key: Any) : StartState
    }

    val uiState = onboardingRepo.completed
        .map { completed -> StartState.Start(if (completed) Main else Onboarding) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StartState.Loading)

    fun markOnboardingCompleted() {
        viewModelScope.launch { onboardingRepo.markCompleted() }
    }
}
