package il.nfm.learnhebrew.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import il.nfm.learnhebrew.ui.theme.LocalAppColors

/**
 * Yellow highlight under the lower portion of the glyph — "saved" word state.
 * Draws a rect from 60% to 92% of the composable height.
 */
fun Modifier.savedHighlight(color: Color? = null): Modifier = composed {
    val yellow = color ?: LocalAppColors.current.yellow
    drawBehind {
        drawRect(
            color = yellow,
            topLeft = Offset(0f, size.height * 0.60f),
            size = androidx.compose.ui.geometry.Size(size.width, size.height * 0.32f),
        )
    }
}

/**
 * Dashed underline — "hard" word state.
 * Drawn 1dp above the composable bottom edge.
 */
fun Modifier.hardUnderline(color: Color? = null): Modifier = composed {
    val mid = color ?: LocalAppColors.current.mid
    drawBehind {
        val y = size.height - 1.dp.toPx()
        drawLine(
            color = mid,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 3.dp.toPx())),
        )
    }
}

/**
 * Solid blue background — "active" (tapped) word state.
 * Add horizontal padding so the fill doesn't clip the glyph.
 */
fun Modifier.activeWord(bg: Color? = null): Modifier = composed {
    val blue = bg ?: LocalAppColors.current.blue
    this
        .drawBehind { drawRect(blue) }
        .padding(horizontal = 2.dp)
}

/**
 * Makes a word tappable with a 48dp minimum hit area (no visual change).
 * Wraps with vertical padding to exploit the 1.85 line-height buffer.
 */
fun Modifier.tappableWord(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    this
        .minimumInteractiveComponentSize()
        .clickable(interactionSource = interactionSource, indication = null) { onClick() }
}
