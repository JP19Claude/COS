package app.shell.fluid.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import app.shell.fluid.designsystem.HapticTokens
import app.shell.fluid.designsystem.MotionTokens
import app.shell.fluid.designsystem.ShellDimens
import app.shell.fluid.designsystem.ShellTheme

/**
 * Switch (DESIGN_SPEC.md §13 "Switch"). Track and thumb are drawn from one animated
 * progress value, so the spring may overshoot without relayout.
 */
@Composable
fun ShellSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = ShellTheme.colors
    val view = LocalView.current
    val progress = animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = MotionTokens.switchToggle.spec(),
        label = "switchToggle",
    )

    Spacer(
        modifier
            .minimumInteractiveComponentSize()
            .size(ShellDimens.switchWidth, ShellDimens.switchHeight)
            .toggleable(
                value = checked,
                interactionSource = null,
                indication = null,
                role = Role.Switch,
                onValueChange = {
                    view.performHapticFeedback(HapticTokens.TOGGLE)
                    onCheckedChange(it)
                },
            )
            .drawBehind {
                val p = progress.value
                val radius = CornerRadius(size.height / 2)
                drawRoundRect(lerp(colors.switchTrackOff, colors.accent, p.coerceIn(0f, 1f)), cornerRadius = radius)
                val inset = ShellDimens.switchThumbInset.toPx()
                val diameter = size.height - 2 * inset
                val travel = size.width - 2 * inset - diameter
                drawCircle(
                    color = colors.switchThumb,
                    radius = diameter / 2,
                    center = Offset(inset + diameter / 2 + travel * p, size.height / 2),
                )
            },
    )
}

@Preview
@Composable
private fun ShellSwitchPreview() {
    ShellTheme {
        Row {
            ShellSwitch(checked = true, onCheckedChange = {})
            ShellSwitch(checked = false, onCheckedChange = {})
        }
    }
}
