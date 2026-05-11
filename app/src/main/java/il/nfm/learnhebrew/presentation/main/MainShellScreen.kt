package il.nfm.learnhebrew.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import il.nfm.learnhebrew.R
import il.nfm.learnhebrew.presentation.generation.GenerationScreen
import il.nfm.learnhebrew.presentation.main.tabs.ReviewScreen
import il.nfm.learnhebrew.presentation.main.tabs.StatsScreen
import il.nfm.learnhebrew.presentation.main.tabs.WordsScreen
import il.nfm.learnhebrew.presentation.navigation.Generation
import il.nfm.learnhebrew.presentation.navigation.Review
import il.nfm.learnhebrew.presentation.navigation.Stats
import il.nfm.learnhebrew.presentation.navigation.Words
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography
import il.nfm.learnhebrew.ui.theme.Sizes
import il.nfm.learnhebrew.ui.theme.Spacing

private val tabs = listOf(Generation, Words, Review, Stats)

@Composable
fun MainShellScreen() {
    val tabBackStack = remember { mutableStateListOf<Any>(Generation) }
    val currentTab = tabBackStack.lastOrNull() ?: Generation

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Box(modifier = Modifier.weight(1f)) {
            NavDisplay(
                backStack = tabBackStack,
                onBack = { tabBackStack.removeLastOrNull() },
                entryProvider = { key ->
                    when (key) {
                        Generation -> NavEntry(key) { GenerationScreen() }
                        Words -> NavEntry(key) { WordsScreen() }
                        Review -> NavEntry(key) { ReviewScreen() }
                        Stats -> NavEntry(key) { StatsScreen() }
                        else -> error("Unknown tab: $key")
                    }
                },
            )
        }
        BottomNavBar(
            activeTab = currentTab,
            onTabSelected = { tab ->
                if (currentTab != tab) {
                    tabBackStack.clear()
                    tabBackStack.add(tab)
                }
            },
        )
    }
}

@Composable
private fun BottomNavBar(activeTab: Any, onTabSelected: (Any) -> Unit) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Sizes.BottomNavH)
            .border(Spacing.Hairline, c.line)
            .background(c.bg)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tabs.forEach { tab ->
            val isActive = tab == activeTab
            val label = when (tab) {
                Generation -> stringResource(R.string.main_tab_read)
                Words -> stringResource(R.string.main_tab_words)
                Review -> stringResource(R.string.main_tab_review)
                Stats -> stringResource(R.string.main_tab_stats)
                else -> ""
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = Spacing.InlineGap),
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(if (isActive) c.blue else c.line2),
                )
                Spacer(Modifier.height(Spacing.Tight))
                Text(
                    label,
                    style = t.metaXs,
                    color = if (isActive) c.ink else c.muted,
                )
            }
        }
    }
}
