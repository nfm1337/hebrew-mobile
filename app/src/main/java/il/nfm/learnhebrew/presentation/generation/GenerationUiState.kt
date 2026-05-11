package il.nfm.learnhebrew.presentation.generation

import androidx.annotation.StringRes
import il.nfm.learnhebrew.R
import il.nfm.learnhebrew.presentation.common.Level
import il.nfm.learnhebrew.presentation.common.Topic

data class GenerationUiState(
    val selectedLevel: Level = Level.A2,
    val selectedTopicId: String? = null,
    val topics: List<Topic> = emptyList(),
    val selectedLength: TextLength = TextLength.Medium,
    val recent: List<RecentTextItem> = emptyList(),
    val isGenerating: Boolean = false,
)

val GenerationUiState.canGenerate: Boolean
    get() = selectedTopicId != null

enum class TextLength(
    @param:StringRes val labelRes: Int,
    @param:StringRes val approxRes: Int,
) {
    Short(R.string.generation_length_short_label, R.string.generation_length_short_words),
    Medium(R.string.generation_length_medium_label, R.string.generation_length_medium_words),
    Long(R.string.generation_length_long_label, R.string.generation_length_long_words),
}

data class RecentTextItem(
    val id: String,
    val dateLabel: String,
    val hebrewTitle: String,
    val metaLine: String,
)
