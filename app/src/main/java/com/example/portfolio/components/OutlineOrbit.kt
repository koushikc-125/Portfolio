package com.example.portfolio.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.portfolio.core.designsystem.component.SubHeading
import kotlinx.coroutines.delay

@Composable
fun OutlineOrbitComponent(
    modifier: Modifier = Modifier,
    showAnimation: Boolean = false,
    content: @Composable () -> Unit = { DefaultContent() }
) {
    val color = MaterialTheme.colorScheme.onPrimary
    val infiniteTransition = rememberInfiniteTransition(label = "dots_movement")

    val dash = 8.dp
    val gap = 4.89.dp
    val density = LocalDensity.current

    val innerDrawingParams = remember(dash, gap, density) {
        OutlineDrawingParams(
            dashPx = with(density) { dash.toPx() },
            gapPx = with(density) { gap.toPx() },
            strokeWidth = with(density) { 2.dp.toPx() },
            cornerRadius = with(density) { 50.dp.toPx() }
        )
    }

    val phaseOffsetPx = if (showAnimation) {
        val animatedOffsetDp by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (dash + gap).value,
            animationSpec = infiniteRepeatable(
                animation = tween(300, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "phase_offset_inner"
        )

        with(density) { animatedOffsetDp.dp.toPx() }
    } else {
        0f
    }


    Box(
        modifier = modifier
            .size(200.dp, 56.dp)
            .background(
                color = color.copy(.05f),
                shape = RoundedCornerShape(25.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {

                    }
                )
            }
            .drawBehind {
                drawRoundRect(
                    color = color,
                    style = Stroke(
                        width = innerDrawingParams.strokeWidth,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(innerDrawingParams.dashPx, innerDrawingParams.gapPx),
                            phaseOffsetPx
                        )
                    ),
                    cornerRadius = CornerRadius(innerDrawingParams.cornerRadius)
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun DefaultContent(
    style: TextStyle = MaterialTheme.typography.labelMedium,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    SubHeading(
        text = "Button",
        style = style,
        color = color,
        contentAlignment = Alignment.Center,
        bottomPadding = 0.dp
    )
}

private data class OutlineDrawingParams(
    val dashPx: Float,
    val gapPx: Float,
    val strokeWidth: Float,
    val cornerRadius: Float
)

data class DashGap(val dash: Dp, val gap: Dp)

fun calculateDashGap(
    width: Dp,
    height: Dp,
    desiredDashUnits: Float = 39.7f,
    gapRatio: Float = 0.38f
): DashGap {
    val perimeter = 2 * (width + height)
    val dashUnit = perimeter / desiredDashUnits
    val gap = dashUnit * gapRatio
    val dash = dashUnit - gap
    return DashGap(dash = dash, gap = gap)
}