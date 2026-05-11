package il.nfm.learnhebrew.presentation.common

import androidx.annotation.StringRes
import il.nfm.learnhebrew.R

data class Topic(
    val id: String,
    @param:StringRes val labelRes: Int,
    val isChosen: Boolean = false,
)

fun defaultTopics(): List<Topic> = listOf(
    Topic("food", R.string.topic_food),
    Topic("history", R.string.topic_history),
    Topic("politics", R.string.topic_politics),
    Topic("tech", R.string.topic_tech),
    Topic("science", R.string.topic_science),
    Topic("culture", R.string.topic_culture),
    Topic("travel", R.string.topic_travel),
    Topic("family", R.string.topic_family),
    Topic("nature", R.string.topic_nature),
    Topic("sport", R.string.topic_sport),
)
