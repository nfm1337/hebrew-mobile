package il.nfm.learnhebrew.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography
import il.nfm.learnhebrew.ui.theme.Sizes
import il.nfm.learnhebrew.ui.theme.Spacing

@Composable
fun SelectionChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedBg: Color = LocalAppColors.current.ink,
    selectedFg: Color = LocalAppColors.current.bg,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current
    Box(
        modifier = modifier
            .height(Sizes.ChipH)
            .background(if (isSelected) selectedBg else c.bg)
            .border(Spacing.Hairline, if (isSelected) selectedBg else c.line2)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            label,
            style = t.bodyS,
            color = if (isSelected) selectedFg else c.ink,
        )
    }
}

@Composable
fun SelectionChipTwoLine(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedBg: Color = LocalAppColors.current.ink,
    selectedFg: Color = LocalAppColors.current.bg,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current
    Box(
        modifier = modifier
            .height(Sizes.ChipH)
            .background(if (isSelected) selectedBg else c.bg)
            .border(Spacing.Hairline, if (isSelected) selectedBg else c.line2)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                title,
                style = t.bodyS.copy(fontWeight = FontWeight.SemiBold),
                color = if (isSelected) selectedFg else c.ink,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                subtitle,
                style = t.metaXs,
                color = c.muted,
            )
        }
    }
}
