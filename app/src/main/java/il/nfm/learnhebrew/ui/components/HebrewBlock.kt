package il.nfm.learnhebrew.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/**
 * Switches layout direction to RTL for Hebrew content blocks.
 * Use around Hebrew text paragraphs; never apply globally (UI is LTR).
 */
@Composable
fun HebrewBlock(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}
