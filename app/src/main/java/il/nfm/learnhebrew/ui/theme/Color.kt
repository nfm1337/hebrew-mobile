package il.nfm.learnhebrew.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object LightColors {
    val Bg = Color(0xFFFAFAF7)
    val Surface = Color(0xFFFFFFFF)
    val Surface2 = Color(0xFFF2F1EC)
    val Ink = Color(0xFF0A0A0A)
    val Ink2 = Color(0xFF2A2A2A)
    val Mid = Color(0xFF5C5C5C)
    val Muted = Color(0xFF8A8A85)
    val Line = Color(0xFFE5E3DB)
    val Line2 = Color(0xFFD6D3C9)
    val Blue = Color(0xFF2D5BFF)
    val BlueInk = Color(0xFFFFFFFF)
    val Yellow = Color(0xFFE8FF3A)
    val YellowInk = Color(0xFF0A0A0A)
    val Red = Color(0xFFD63333)
    val Green = Color(0xFF167A4A)
}

object DarkColors {
    val Bg = Color(0xFF0E0F12)
    val Surface = Color(0xFF15171B)
    val Surface2 = Color(0xFF1C1F25)
    val Ink = Color(0xFFE8E6DE)
    val Ink2 = Color(0xFFC9C7BF)
    val Mid = Color(0xFF8E8C84)
    val Muted = Color(0xFF5E5C56)
    val Line = Color(0xFF24272E)
    val Line2 = Color(0xFF2E323A)
    val Blue = Color(0xFF5A82FF)
    val BlueInk = Color(0xFF0E0F12)
    val Yellow = Color(0xFFE8FF3A)
    val YellowInk = Color(0xFF0A0A0A)
    val Red = Color(0xFFE64545)
    val Green = Color(0xFF3FAA72)
}

// ── Semantic token container ──────────────────────────────────────────────────

data class AppColors(
    val bg: Color,
    val surface: Color,
    val surface2: Color,
    val ink: Color,
    val ink2: Color,
    val mid: Color,
    val muted: Color,
    val line: Color,
    val line2: Color,
    val blue: Color,
    val blueInk: Color,
    val yellow: Color,
    val yellowInk: Color,
    val red: Color,
    val green: Color,
)

fun lightAppColors() = AppColors(
    bg = LightColors.Bg,
    surface = LightColors.Surface,
    surface2 = LightColors.Surface2,
    ink = LightColors.Ink,
    ink2 = LightColors.Ink2,
    mid = LightColors.Mid,
    muted = LightColors.Muted,
    line = LightColors.Line,
    line2 = LightColors.Line2,
    blue = LightColors.Blue,
    blueInk = LightColors.BlueInk,
    yellow = LightColors.Yellow,
    yellowInk = LightColors.YellowInk,
    red = LightColors.Red,
    green = LightColors.Green,
)

fun darkAppColors() = AppColors(
    bg = DarkColors.Bg,
    surface = DarkColors.Surface,
    surface2 = DarkColors.Surface2,
    ink = DarkColors.Ink,
    ink2 = DarkColors.Ink2,
    mid = DarkColors.Mid,
    muted = DarkColors.Muted,
    line = DarkColors.Line,
    line2 = DarkColors.Line2,
    blue = DarkColors.Blue,
    blueInk = DarkColors.BlueInk,
    yellow = DarkColors.Yellow,
    yellowInk = DarkColors.YellowInk,
    red = DarkColors.Red,
    green = DarkColors.Green,
)

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("AppColors not provided — wrap with LearnHebrewTheme")
}

// ── Stats activity-grid intensity ─────────────────────────────────────────────

fun activityTint(level: Int, blue: Color, surface2: Color): Color = when (level) {
    0 -> surface2
    1 -> blue.copy(alpha = 0.35f)
    2 -> blue.copy(alpha = 0.60f)
    else -> blue
}
