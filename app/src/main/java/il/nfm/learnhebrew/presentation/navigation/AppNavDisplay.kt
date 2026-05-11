package il.nfm.learnhebrew.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import il.nfm.learnhebrew.presentation.main.MainShellScreen
import il.nfm.learnhebrew.presentation.onboarding.OnboardingScreen

@Composable
fun AppNavDisplay(startKey: Any, onOnboardingDone: () -> Unit) {
    val backStack = remember { mutableStateListOf(startKey) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                Onboarding -> NavEntry(key) {
                    OnboardingScreen(onProceed = {
                        onOnboardingDone()
                        backStack.clear()
                        backStack.add(Main)
                    })
                }
                Main -> NavEntry(key) { MainShellScreen() }
                else -> error("Unknown top-level route: $key")
            }
        },
    )
}
