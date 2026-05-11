package il.nfm.learnhebrew.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import il.nfm.learnhebrew.ui.theme.LocalAppColors
import il.nfm.learnhebrew.ui.theme.LocalAppTypography

@Composable
fun SectionHeader(
    label: String,
    modifier: Modifier = Modifier,
    trailing: String? = null,
    trailingColor: Color = LocalAppColors.current.muted,
) {
    val c = LocalAppColors.current
    val t = LocalAppTypography.current
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, style = t.meta, color = c.mid)
        if (trailing != null) {
            Text(trailing, style = t.meta, color = trailingColor)
        }
    }
}
