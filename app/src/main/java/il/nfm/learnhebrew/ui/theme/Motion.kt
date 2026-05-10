package il.nfm.learnhebrew.ui.theme

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween

object Motion {
    // Bottom sheet slide in: 250ms EaseOutQuart approximation
    fun <T> sheetEnter() = tween<T>(durationMillis = 250, easing = FastOutSlowInEasing)

    // Bottom sheet slide out: 180ms EaseInQuart approximation
    fun <T> sheetExit() = tween<T>(durationMillis = 180, easing = FastOutLinearInEasing)

    // Background scrim fade: 180ms linear
    fun <T> dimFade() = tween<T>(durationMillis = 180, easing = LinearEasing)

    // Tab / picker chip selection: 120ms linear
    fun <T> pickerSelect() = tween<T>(durationMillis = 120, easing = LinearEasing)
}
