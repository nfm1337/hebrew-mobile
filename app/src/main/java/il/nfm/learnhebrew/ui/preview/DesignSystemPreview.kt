package il.nfm.learnhebrew.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import il.nfm.learnhebrew.ui.components.HebrewBlock
import il.nfm.learnhebrew.ui.components.activeWord
import il.nfm.learnhebrew.ui.components.hardUnderline
import il.nfm.learnhebrew.ui.components.savedHighlight
import il.nfm.learnhebrew.ui.components.tappableWord
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography
import il.nfm.learnhebrew.ui.theme.Spacing
import il.nfm.learnhebrew.ui.theme.activityTint

@Composable
fun DesignSystemPreview() {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(c.bg)
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Spacing.GutterH, vertical = Spacing.SectionGap),
        verticalArrangement = Arrangement.spacedBy(Spacing.SectionGap),
    ) {

        // ── Header ────────────────────────────────────────────────────────────
        Text("DESIGN SYSTEM", style = t.meta, color = c.muted)
        Text("Иврит·Read", style = t.display2, color = c.ink)

        Divider(c.line)

        // ── Palette ───────────────────────────────────────────────────────────
        SectionLabel("ПАЛИТРА", c, t)
        val swatches = listOf(
            "bg" to c.bg, "surface" to c.surface, "surface2" to c.surface2,
            "ink" to c.ink, "ink2" to c.ink2, "mid" to c.mid, "muted" to c.muted,
            "line" to c.line, "line2" to c.line2,
            "blue" to c.blue, "blueInk" to c.blueInk,
            "yellow" to c.yellow, "yellowInk" to c.yellowInk,
            "red" to c.red, "green" to c.green,
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            swatches.chunked(5).forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    row.forEach { (name, color) ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(color)
                                    .border(Spacing.Hairline, c.line2),
                            )
                            Text(
                                name,
                                style = t.metaXs,
                                color = c.mid,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }

        Divider(c.line)

        // ── Typography ────────────────────────────────────────────────────────
        SectionLabel("ТИПОГРАФИКА", c, t)
        val typeTokens = listOf(
            "displayHero" to t.displayHero,
            "display1" to t.display1,
            "display2" to t.display2,
            "displayHe1" to t.displayHe1,
            "displayHe2" to t.displayHe2,
            "numL" to t.numL,
            "numM" to t.numM,
            "numS" to t.numS,
            "bodyHe" to t.bodyHe,
            "bodyHeReview" to t.bodyHeReview,
            "body" to t.body,
            "bodyM" to t.bodyM,
            "bodyS" to t.bodyS,
            "cta" to t.cta,
            "tab" to t.tab,
            "meta" to t.meta,
            "metaXs" to t.metaXs,
            "metaNum" to t.metaNum,
        )
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.BlockGap)) {
            typeTokens.forEach { (name, style) ->
                Column {
                    Text(name.uppercase(), style = t.metaXs, color = c.muted)
                    Text("Abc 123 אבג", style = style, color = c.ink)
                }
            }
        }

        Divider(c.line)

        // ── Buttons ───────────────────────────────────────────────────────────
        SectionLabel("КНОПКИ", c, t)
        // Primary CTA
        Button(
            onClick = {},
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = c.blue,
                contentColor = c.blueInk,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            Text("Начать урок", style = t.cta)
        }
        Spacer(Modifier.height(Spacing.InlineGap))
        // Ghost button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(Spacing.Hairline, c.line2, RectangleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text("Пропустить", style = t.cta, color = c.ink2)
        }

        Divider(c.line)

        // ── Hebrew block ──────────────────────────────────────────────────────
        SectionLabel("ИВРИТ + RTL", c, t)
        HebrewBlock {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(c.surface)
                    .padding(Spacing.BlockGap),
                horizontalAlignment = Alignment.End,
            ) {
                Text("שָׁלוֹם עוֹלָם", style = t.displayHe1, color = c.ink)
                Spacer(Modifier.height(Spacing.RowGap))
                Text(
                    "בְּרֵאשִׁית בָּרָא אֱלֹהִים אֵת הַשָּׁמַיִם וְאֵת הָאָרֶץ",
                    style = t.bodyHe,
                    color = c.ink,
                )
            }
        }

        // ── Word states ───────────────────────────────────────────────────────
        SectionLabel("СОСТОЯНИЯ СЛОВА", c, t)
        var activeIdx by remember { mutableIntStateOf(-1) }
        HebrewBlock {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.InlineGap, Alignment.End),
            ) {
                val words = listOf("שָׁלוֹם" to 0, "עוֹלָם" to 1, "בֵּית" to 2, "סֵפֶר" to 3)
                words.forEach { (word, idx) ->
                    val mod = when {
                        activeIdx == idx -> Modifier.activeWord(c.blue)
                        idx == 1 -> Modifier.savedHighlight(c.yellow)
                        idx == 2 -> Modifier.hardUnderline(c.mid)
                        else -> Modifier
                    }
                    Text(
                        word,
                        style = t.bodyHe,
                        color = if (activeIdx == idx) c.blueInk else c.ink,
                        modifier = mod.tappableWord {
                            activeIdx = if (activeIdx == idx) -1 else idx
                        },
                    )
                }
            }
        }
        Text("↑ Тап по слову — активное состояние (синее)", style = t.bodyS, color = c.muted)

        Divider(c.line)

        // ── Activity grid ─────────────────────────────────────────────────────
        SectionLabel("ACTIVITY GRID (STATS)", c, t)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            (0..6).forEach { day ->
                val level = day % 4
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(activityTint(level, c.blue, c.surface2))
                        .border(Spacing.Hairline, c.line),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(level.toString(), style = t.metaXs, color = c.ink2)
                }
            }
        }

        Divider(c.line)

        // ── Bottom-nav demo ───────────────────────────────────────────────────
        SectionLabel("BOTTOM NAV", c, t)
        var selectedNav by remember { mutableStateOf("ЧИТАТЬ") }
        val navItems = listOf("ЧИТАТЬ", "СЛОВА", "ПОВТОР", "СТАТЫ")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(c.surface)
                .border(Spacing.Hairline, c.line, RectangleShape),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                navItems.forEach { label ->
                    val isActive = label == selectedNav
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.tappableWord { selectedNav = label },
                    ) {
                        if (isActive) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(c.blue),
                            )
                            Spacer(Modifier.height(4.dp))
                        } else {
                            Spacer(Modifier.height(10.dp))
                        }
                        Text(
                            label,
                            style = t.metaXs,
                            color = if (isActive) c.blue else c.muted,
                        )
                    }
                }
            }
        }

        // ── Big number demo ───────────────────────────────────────────────────
        Divider(c.line)
        SectionLabel("DISPLAYHERO (STATS)", c, t)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.SectionGap),
            verticalAlignment = Alignment.Bottom,
        ) {
            Column {
                Text("247", style = t.displayHero, color = c.ink)
                Text("СЛОВ", style = t.meta, color = c.muted)
            }
            Column {
                Text("32", style = t.numM, color = c.blue)
                Text("СЕРИЯ", style = t.meta, color = c.muted)
            }
            Column {
                Text("A2", style = t.numL, color = c.ink2)
                Text("УРОВЕНЬ", style = t.meta, color = c.muted)
            }
        }

        Spacer(Modifier.height(Spacing.SectionGap))
    }
}

// ── Internal helpers ──────────────────────────────────────────────────────────

@Composable
private fun Divider(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color),
    )
}

@Composable
private fun SectionLabel(
    label: String,
    c: il.nfm.learnhebrew.ui.theme.AppColors,
    t: il.nfm.learnhebrew.ui.theme.AppTypography,
) {
    Text(label, style = t.meta, color = c.mid)
}
