package il.nfm.learnhebrew.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// ── Helpers ───────────────────────────────────────────────────────────────────

private val noFontPadding = PlatformTextStyle(includeFontPadding = false)

private fun style(
    family: androidx.compose.ui.text.font.FontFamily,
    size: TextUnit,
    weight: FontWeight,
    lineHeightMult: Float,
    letterSpacingEm: Float = 0f,
    featureSettings: String = "",
): TextStyle = TextStyle(
    fontFamily = family,
    fontSize = size,
    fontWeight = weight,
    lineHeight = (size.value * lineHeightMult).sp,
    letterSpacing = letterSpacingEm.em,
    fontFeatureSettings = featureSettings,
    platformStyle = noFontPadding,
)

// ── Token container ───────────────────────────────────────────────────────────

data class AppTypography(
    // ── Display / Inter Tight ─────────────────────────────────────────────────
    /** Stats — big number (247). 96sp SemiBold, lineHeight×0.85, ls -0.05em */
    val displayHero: TextStyle,
    /** Onboarding — "Какой у тебя уровень?". 48sp SemiBold, lineHeight×0.95, ls -0.035em */
    val display1: TextStyle,
    /** Home — "Новый текст". 44sp SemiBold, lineHeight×0.95, ls -0.035em */
    val display2: TextStyle,
    /** A1/A2/B1 codes in onboarding. 36sp SemiBold, lineHeight×0.9, ls -0.05em */
    val numL: TextStyle,
    /** Stats status counters. 32sp SemiBold, lineHeight×0.9, ls -0.05em */
    val numM: TextStyle,
    /** Vocab word counter. 64sp SemiBold, lineHeight×0.9, ls -0.05em */
    val numS: TextStyle,
    /** Primary button label. 16sp SemiBold, lineHeight×1.0, ls -0.01em */
    val cta: TextStyle,
    /** Tabs row. 13sp Medium, lineHeight×1.0, ls -0.005em */
    val tab: TextStyle,

    // ── Hebrew / Noto Serif Hebrew ────────────────────────────────────────────
    /** Reading — Hebrew heading. 44sp Medium, lineHeight×1.05 */
    val displayHe1: TextStyle,
    /** Review / WordCard — target word. 60sp Medium, lineHeight×1.0 */
    val displayHe2: TextStyle,
    /** Reading body text and vocab lemma. 22sp Normal, lineHeight×1.85 */
    val bodyHe: TextStyle,
    /** Review sentence context. 24sp Normal, lineHeight×1.7 */
    val bodyHeReview: TextStyle,

    // ── Body / Inter ──────────────────────────────────────────────────────────
    /** Standard UI text. 14sp Normal, lineHeight×1.45 */
    val body: TextStyle,
    /** Translation in Review. 15sp Normal, lineHeight×1.5 */
    val bodyM: TextStyle,
    /** Secondary text. 13sp Normal, lineHeight×1.4 */
    val bodyS: TextStyle,

    // ── Meta / JetBrains Mono ─────────────────────────────────────────────────
    /** Masthead, labels (use UPPERCASE). 11sp Medium, ls 0.04em */
    val meta: TextStyle,
    /** Bottom-nav, small codes (UPPERCASE). 10sp Medium, ls 0.04em */
    val metaXs: TextStyle,
    /** Row values in Stats. 13sp Normal, ls -0.02em */
    val metaNum: TextStyle,
)

fun appTypography(): AppTypography = AppTypography(
    // Inter Tight — display
    displayHero = style(InterTight, 96.sp, FontWeight.SemiBold, 0.85f, -0.05f, "tnum"),
    display1 = style(InterTight, 48.sp, FontWeight.SemiBold, 0.95f, -0.035f),
    display2 = style(InterTight, 44.sp, FontWeight.SemiBold, 0.95f, -0.035f),
    numL = style(InterTight, 36.sp, FontWeight.SemiBold, 0.90f, -0.05f, "tnum"),
    numM = style(InterTight, 32.sp, FontWeight.SemiBold, 0.90f, -0.05f, "tnum"),
    numS = style(InterTight, 64.sp, FontWeight.SemiBold, 0.90f, -0.05f, "tnum"),
    cta = style(InterTight, 16.sp, FontWeight.SemiBold, 1.00f, -0.01f),
    tab = style(InterTight, 13.sp, FontWeight.Medium, 1.00f, -0.005f),

    // Noto Serif Hebrew
    displayHe1 = style(NotoSerifHebrew, 44.sp, FontWeight.Medium, 1.05f),
    displayHe2 = style(NotoSerifHebrew, 60.sp, FontWeight.Medium, 1.00f),
    bodyHe = style(NotoSerifHebrew, 22.sp, FontWeight.Normal, 1.85f),
    bodyHeReview = style(NotoSerifHebrew, 24.sp, FontWeight.Normal, 1.70f),

    // Inter — body
    body = style(Inter, 14.sp, FontWeight.Normal, 1.45f),
    bodyM = style(Inter, 15.sp, FontWeight.Normal, 1.50f),
    bodyS = style(Inter, 13.sp, FontWeight.Normal, 1.40f),

    // JetBrains Mono — meta (tnum for all; use with .uppercase() on text)
    meta = style(JetBrainsMono, 11.sp, FontWeight.Medium, 1.00f, 0.04f, "tnum"),
    metaXs = style(JetBrainsMono, 10.sp, FontWeight.Medium, 1.00f, 0.04f, "tnum"),
    metaNum = style(JetBrainsMono, 13.sp, FontWeight.Normal, 1.00f, -0.02f, "tnum"),
)

// ── CompositionLocal ──────────────────────────────────────────────────────────

val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("AppTypography not provided — wrap with LearnHebrewTheme")
}

// ── Bridge to Material Typography ─────────────────────────────────────────────
// Maps our tokens onto Material3 slots so Material components inherit correct fonts.
// Many tokens don't map 1-to-1; display slots get our closest display equivalent.

fun AppTypography.toMaterialTypography() = Typography(
    displayLarge = display1,         // 48sp — largest Roman display
    displayMedium = display2,         // 44sp
    displaySmall = numL,             // 36sp — closest slot
    headlineLarge = numM,            // 32sp
    headlineMedium = tab,             // closest for section heads
    headlineSmall = tab,
    titleLarge = bodyM,           // 15sp — dialog titles
    titleMedium = body,
    titleSmall = bodyS,
    bodyLarge = bodyM,           // 15sp
    bodyMedium = body,            // 14sp — default body
    bodySmall = bodyS,           // 13sp
    labelLarge = cta,             // 16sp SemiBold — primary buttons
    labelMedium = tab,             // 13sp — chips, tabs
    labelSmall = meta,            // 11sp Mono — small labels
)
