package il.nfm.learnhebrew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import il.nfm.learnhebrew.presentation.MainViewModel
import il.nfm.learnhebrew.presentation.navigation.AppNavDisplay
import il.nfm.learnhebrew.ui.theme.LearnHebrewTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnHebrewTheme {
                val state by vm.uiState.collectAsState()
                Surface(color = MaterialTheme.colorScheme.background) {
                    when (val s = state) {
                        MainViewModel.StartState.Loading -> {}
                        is MainViewModel.StartState.Start -> AppNavDisplay(
                            startKey = s.key,
                            onOnboardingDone = { vm.markOnboardingCompleted() },
                        )
                    }
                }
            }
        }
    }
}
