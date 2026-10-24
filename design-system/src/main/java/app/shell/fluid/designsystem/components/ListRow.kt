package app.shell.fluid.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.shell.fluid.designsystem.ShellDimens
import app.shell.fluid.designsystem.ShellTheme

/** Settings list row (DESIGN_SPEC.md §13 "Einstellungsliste"). */
@Composable
fun ListRow(
    title: String,
    modifier: Modifier = Modifier,
    summary: String? = null,
    onClick: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = ShellDimens.rowMinHeight)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = ShellDimens.rowPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(text = title, style = ShellTheme.typography.body, color = ShellTheme.colors.textPrimary)
            if (summary != null) {
                Spacer(Modifier.height(ShellDimens.rowSummaryGap))
                Text(text = summary, style = ShellTheme.typography.label, color = ShellTheme.colors.textSecondary)
            }
        }
        trailing?.invoke()
    }
}

@Preview
@Composable
private fun ListRowPreview() {
    ShellTheme {
        ListRow(title = "Titel", summary = "Untertitel", trailing = { ShellSwitch(checked = true, onCheckedChange = {}) })
    }
}
