package il.nfm.learnhebrew.presentation.common

import androidx.annotation.StringRes
import il.nfm.learnhebrew.R

enum class Level(
    val code: String,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
) {
    A1("A1", R.string.level_a1_title, R.string.level_a1_description),
    A2("A2", R.string.level_a2_title, R.string.level_a2_description),
    B1("B1", R.string.level_b1_title, R.string.level_b1_description),
}
