package il.nfm.learnhebrew.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import il.nfm.learnhebrew.R
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import il.nfm.learnhebrew.ui.theme.LearnHebrewTheme
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography
import il.nfm.learnhebrew.ui.theme.Sizes
import il.nfm.learnhebrew.ui.theme.Spacing

@Composable
fun OnboardingScreen(
    onProceed: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OnboardingScreenContent(
        uiState = uiState,
        onLevelSelect = viewModel::selectLevel,
        onTopicToggle = viewModel::toggleTopic,
        onProceed = onProceed,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreenContent(
    uiState: OnboardingUiState,
    onLevelSelect: (Level) -> Unit,
    onTopicToggle: (String) -> Unit,
    onProceed: () -> Unit,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(c.bg)
            .windowInsetsPadding(WindowInsets.statusBars)
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Spacing.GutterH),
        ) {
            Spacer(Modifier.height(Spacing.BlockGap))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(stringResource(R.string.onboarding_header_label), style = t.meta, color = c.muted)
                Text(stringResource(R.string.onboarding_step_indicator), style = t.meta, color = c.muted)
            }

            Spacer(Modifier.height(Spacing.SectionGap))

            Text(stringResource(R.string.onboarding_level_title), style = t.display1, color = c.ink)

            Spacer(Modifier.height(Spacing.BlockGap))

            Text(
                stringResource(R.string.onboarding_level_subtitle),
                style = t.body,
                color = c.muted,
            )

            Spacer(Modifier.height(Spacing.SectionGap))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(Spacing.Hairline, c.line),
            ) {
                Level.entries.forEachIndexed { index, level ->
                    if (index > 0) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(Spacing.Hairline)
                                .background(c.line),
                        )
                    }
                    LevelPickerRow(
                        level = level,
                        isSelected = uiState.selectedLevel == level,
                        onClick = { onLevelSelect(level) },
                    )
                }
            }

            Spacer(Modifier.height(Spacing.SectionGap))

            val chosenCount = uiState.topics.count { it.isChosen }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringResource(R.string.onboarding_topics_header), style = t.meta, color = c.mid)
                Text(
                    stringResource(R.string.onboarding_topics_chosen_count, chosenCount, MIN_TOPICS_REQUIRED),
                    style = t.meta,
                    color = if (chosenCount >= MIN_TOPICS_REQUIRED) c.green else c.muted,
                )
            }

            Spacer(Modifier.height(Spacing.BlockGap))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
                verticalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
            ) {
                uiState.topics.forEach { topic ->
                    TopicChip(
                        topic = topic,
                        onClick = { onTopicToggle(topic.id) },
                    )
                }
            }

            Spacer(Modifier.height(Spacing.SectionGap))
        }

        Button(
            onClick = onProceed,
            enabled = uiState.canProceed,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = c.blue,
                contentColor = c.blueInk,
                disabledContainerColor = c.surface2,
                disabledContentColor = c.muted,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(Sizes.PrimaryBtnH),
        ) {
            Text(stringResource(R.string.onboarding_proceed_button), style = t.cta)
        }
    }
}

@Composable
private fun LevelPickerRow(
    level: Level,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) c.ink else c.bg)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.GutterH, vertical = Spacing.BlockGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            level.label,
            style = t.numL,
            color = if (isSelected) c.bg else c.ink2,
            modifier = Modifier.width(56.dp),
        )

        Spacer(Modifier.width(Spacing.BlockGap))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                level.title,
                style = t.body.copy(fontWeight = FontWeight.SemiBold),
                color = if (isSelected) c.bg else c.ink2,
            )
            Spacer(Modifier.height(Spacing.Tight))
            Text(
                level.description,
                style = t.bodyS,
                color = c.muted,
            )
        }

        if (isSelected) {
            Spacer(Modifier.width(Spacing.InlineGap))
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(c.bg, CircleShape),
            )
        }
    }
}

@Composable
private fun TopicChip(
    topic: Topic,
    onClick: () -> Unit,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Box(
        modifier = Modifier
            .height(Sizes.ChipH)
            .background(if (topic.isChosen) c.yellow else c.bg)
            .border(Spacing.Hairline, if (topic.isChosen) c.yellow else c.line2)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            topic.label,
            style = t.bodyS,
            color = c.yellowInk,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F12)
@Composable
private fun OnboardingScreenDarkPreview() {
    LearnHebrewTheme(darkTheme = true) {
        OnboardingScreenContent(
            uiState = OnboardingUiState(
                selectedLevel = Level.A2,
                topics = defaultTopics().mapIndexed { i, t -> t.copy(isChosen = i < 2) },
            ),
            onLevelSelect = {},
            onTopicToggle = {},
            onProceed = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAFAF7)
@Composable
private fun OnboardingScreenLightPreview() {
    LearnHebrewTheme(darkTheme = false) {
        OnboardingScreenContent(
            uiState = OnboardingUiState(
                selectedLevel = Level.A1,
                topics = defaultTopics(),
            ),
            onLevelSelect = {},
            onTopicToggle = {},
            onProceed = {},
        )
    }
}
