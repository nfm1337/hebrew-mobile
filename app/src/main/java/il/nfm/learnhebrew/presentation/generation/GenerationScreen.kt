package il.nfm.learnhebrew.presentation.generation

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import il.nfm.learnhebrew.R
import il.nfm.learnhebrew.presentation.common.Level
import il.nfm.learnhebrew.presentation.common.defaultTopics
import il.nfm.learnhebrew.ui.components.HebrewBlock
import il.nfm.learnhebrew.ui.components.SectionHeader
import il.nfm.learnhebrew.ui.components.SelectionChip
import il.nfm.learnhebrew.ui.components.SelectionChipTwoLine
import il.nfm.learnhebrew.ui.theme.LearnHebrewTheme
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography
import il.nfm.learnhebrew.ui.theme.Sizes
import il.nfm.learnhebrew.ui.theme.Spacing

@Composable
fun GenerationScreen(
    viewModel: GenerationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val mockItems = mockRecentItems()
    LaunchedEffect(Unit) { viewModel.setRecent(mockItems) }

    GenerationScreenContent(
        uiState = uiState,
        onLevelSelect = viewModel::selectLevel,
        onTopicSelect = viewModel::selectTopic,
        onLengthSelect = viewModel::selectLength,
        onGenerate = viewModel::generate,
        onRecentClick = {},
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenerationScreenContent(
    uiState: GenerationUiState,
    onLevelSelect: (Level) -> Unit,
    onTopicSelect: (String) -> Unit,
    onLengthSelect: (TextLength) -> Unit,
    onGenerate: () -> Unit,
    onRecentClick: (String) -> Unit,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(c.bg)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(modifier = Modifier.padding(horizontal = Spacing.GutterH)) {
                Spacer(Modifier.height(Spacing.BlockGap))

                SectionHeader(
                    label = stringResource(R.string.generation_header_label),
                    trailing = stringResource(R.string.generation_edition_label),
                )

                Spacer(Modifier.height(Spacing.SectionGap))

                val titleRaw = stringResource(R.string.generation_title)
                val titleText = buildAnnotatedString {
                    val dotIndex = titleRaw.lastIndexOf('.')
                    append(titleRaw)
                    if (dotIndex >= 0) {
                        addStyle(SpanStyle(color = c.muted), dotIndex, dotIndex + 1)
                    }
                }
                Text(titleText, style = t.display2, color = c.ink)

                Spacer(Modifier.height(Spacing.SectionGap))

                SectionHeader(label = stringResource(R.string.generation_section_level))

                Spacer(Modifier.height(Spacing.BlockGap))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
                ) {
                    Level.entries.forEach { level ->
                        SelectionChip(
                            label = level.code,
                            isSelected = uiState.selectedLevel == level,
                            onClick = { onLevelSelect(level) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                Spacer(Modifier.height(Spacing.SectionGap))

                SectionHeader(label = stringResource(R.string.generation_section_topic))

                Spacer(Modifier.height(Spacing.BlockGap))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
                    verticalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
                ) {
                    uiState.topics.forEach { topic ->
                        SelectionChip(
                            label = stringResource(topic.labelRes),
                            isSelected = topic.id == uiState.selectedTopicId,
                            onClick = { onTopicSelect(topic.id) },
                        )
                    }
                }

                Spacer(Modifier.height(Spacing.SectionGap))

                SectionHeader(label = stringResource(R.string.generation_section_length))

                Spacer(Modifier.height(Spacing.BlockGap))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.InlineGap),
                ) {
                    TextLength.entries.forEach { length ->
                        SelectionChipTwoLine(
                            title = stringResource(length.labelRes),
                            subtitle = stringResource(length.approxRes),
                            isSelected = uiState.selectedLength == length,
                            onClick = { onLengthSelect(length) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                Spacer(Modifier.height(Spacing.SectionGap))
            }

            Button(
                onClick = onGenerate,
                enabled = uiState.canGenerate,
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
                Text(stringResource(R.string.generation_cta) + "  →", style = t.cta)
            }

            if (uiState.recent.isNotEmpty()) {
                Column(modifier = Modifier.padding(horizontal = Spacing.GutterH)) {
                    Spacer(Modifier.height(Spacing.SectionGap))

                    SectionHeader(
                        label = stringResource(R.string.generation_section_recent),
                        trailing = uiState.recent.size.toString(),
                    )

                    uiState.recent.forEach { item ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(Spacing.Hairline)
                                .background(c.line),
                        )
                        RecentTextRow(item = item, onClick = { onRecentClick(item.id) })
                    }
                }
            }

            Spacer(Modifier.height(Spacing.SectionGap))
        }

        BottomNavBar()
    }
}

@Composable
private fun RecentTextRow(
    item: RecentTextItem,
    onClick: () -> Unit,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = Spacing.RowGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            item.dateLabel,
            style = t.metaXs,
            color = c.muted,
            modifier = Modifier.width(56.dp),
        )

        Column(modifier = Modifier.weight(1f)) {
            HebrewBlock {
                Text(item.hebrewTitle, style = t.displayHe1, color = c.ink)
            }
            Spacer(Modifier.height(Spacing.Tight))
            Text(item.metaLine, style = t.metaXs, color = c.muted)
        }

        Spacer(Modifier.width(Spacing.InlineGap))
        Text("→", style = t.body, color = c.muted)
    }
}

private enum class NavTab(val labelRes: Int) {
    Read(R.string.generation_tab_read),
    Review(R.string.generation_tab_review),
    Vocab(R.string.generation_tab_vocab),
    Me(R.string.generation_tab_me),
}

@Composable
private fun BottomNavBar(activeTab: NavTab = NavTab.Read) {
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
        NavTab.entries.forEach { tab ->
            val isActive = tab == activeTab
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = Spacing.InlineGap),
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(if (isActive) c.blue else c.line2),
                )
                Spacer(Modifier.height(Spacing.Tight))
                Text(
                    stringResource(tab.labelRes),
                    style = t.metaXs,
                    color = if (isActive) c.ink else c.muted,
                )
            }
        }
    }
}

@Composable
private fun mockRecentItems(): List<RecentTextItem> {
    val historyLabel = stringResource(R.string.topic_history).uppercase()
    val foodLabel = stringResource(R.string.topic_food).uppercase()
    val wordsPattern = stringResource(R.string.generation_recent_words_mask)
    return listOf(
        RecentTextItem(
            id = "1",
            dateLabel = "08 МАЯ",
            hebrewTitle = "בָּעֲלִיָּה הָרִאשׁוֹנָה",
            metaLine = "A2 · $historyLabel · ${wordsPattern.format(8)}",
        ),
        RecentTextItem(
            id = "2",
            dateLabel = "07 МАЯ",
            hebrewTitle = "הַתַּחֲנָה הַמֶּרְכָּזִית",
            metaLine = "A2 · $foodLabel · ${wordsPattern.format(11)}",
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F12)
@Composable
private fun GenerationScreenDarkPreview() {
    LearnHebrewTheme(darkTheme = true) {
        GenerationScreenContent(
            uiState = GenerationUiState(
                selectedLevel = Level.A2,
                selectedTopicId = "food",
                topics = defaultTopics(),
                selectedLength = TextLength.Medium,
                recent = listOf(
                    RecentTextItem("1", "08 МАЯ", "בָּעֲלִיָּה הָרִאשׁוֹנָה", "A2 · ИСТОРИЯ · 8 НОВ."),
                    RecentTextItem("2", "07 МАЯ", "הַתַּחֲנָה הַמֶּרְכָּזִית", "A2 · ЕДА · 11 НОВ."),
                ),
            ),
            onLevelSelect = {},
            onTopicSelect = {},
            onLengthSelect = {},
            onGenerate = {},
            onRecentClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAFAF7)
@Composable
private fun GenerationScreenLightPreview() {
    LearnHebrewTheme(darkTheme = false) {
        GenerationScreenContent(
            uiState = GenerationUiState(
                selectedLevel = Level.A2,
                selectedTopicId = "food",
                topics = defaultTopics(),
                selectedLength = TextLength.Medium,
                recent = listOf(
                    RecentTextItem("1", "08 МАЯ", "בָּעֲלִיָּה הָרִאשׁוֹנָה", "A2 · ИСТОРИЯ · 8 НОВ."),
                    RecentTextItem("2", "07 МАЯ", "הַתַּחֲנָה הַמֶּרְכָּזִית", "A2 · ЕДА · 11 НОВ."),
                ),
            ),
            onLevelSelect = {},
            onTopicSelect = {},
            onLengthSelect = {},
            onGenerate = {},
            onRecentClick = {},
        )
    }
}
