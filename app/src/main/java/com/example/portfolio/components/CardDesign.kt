package com.example.portfolio.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.core.designsystem.component.ThemePreview
import com.example.portfolio.core.designsystem.theme.PortfolioTheme

@Composable
fun CardDesign(
    showAnimation: Boolean = false,
) {
    val animationProgress = remember { Animatable(if (showAnimation) 0f else 1f) }
    var isShowing by remember { mutableStateOf(false) }

    if (showAnimation) {
        LaunchedEffect(Unit) {
            isShowing = true
            animationProgress.animateTo(1f, tween(3000))
        }
    }

    val color = MaterialTheme.colorScheme.onPrimary

    val colorAlpha = color.copy(.8f)

    val color1 = MaterialTheme.colorScheme.surface
    val color2 = MaterialTheme.colorScheme.onSurface
    val color3 = MaterialTheme.colorScheme.onSecondary

    Box(
        modifier = Modifier
            .fillMaxWidth(.88f)
            .fillMaxHeight(.6f)
            .shadow(
                elevation = 6.dp, shape = RoundedCornerShape(14.dp), clip = true
            )
            .background(
                color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(14.dp)
            )
            .padding(
                top = 12.dp, start = 14.dp
            ), contentAlignment = Alignment.TopStart
    ) {
        Column {
            AnimatingTextHeading(
                showAnimation = isShowing,
            )
            Spacer(Modifier.height(4.dp))
            AnimatingTextSubHeading(
                showAnimation = isShowing,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val path = linePath(size)
                        val pathMeasure = PathMeasure()
                        pathMeasure.setPath(path, false)
                        val filledPath = Path().apply {
                            addPath(path)
                            relativeLineTo(0f, size.height)
                            lineTo(0f, size.height)
                            close()
                        }

                        onDrawBehind {
                            val currentDistance = pathMeasure.length * animationProgress.value
                            val position = pathMeasure.getPosition(currentDistance)

                            val clippingWidth = if (currentDistance > 0) position.x else 0f
                            val gradientBrush = Brush.linearGradient(
                                colors = listOf(
                                    color1,
                                    color2,
                                    color3
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(size.width, size.height)
                            )


                            clipRect(right = clippingWidth) {
                                drawPath(
                                    path = path,
                                    brush = gradientBrush,
                                    style = Stroke(
                                        width = 3.dp.toPx(),
                                        cap = StrokeCap.Round,
                                        join = StrokeJoin.Round
                                    )
                                )

                                drawPath(
                                    filledPath, brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            colorAlpha.copy(alpha = 0.6f), Color.Transparent
                                        ), endX = size.width * 0.7f
                                    ), style = Fill
                                )
                            }
                            if (currentDistance > 0) {
                                drawCircle(

                                    color = color,
                                    radius = 4.dp.toPx(),
                                    center = position
                                )
                            }
                        }
                    }
            )
        }
    }
}

@Composable
private fun AnimatingTextHeading(
    showAnimation: Boolean,
) {
    var isVisible by remember { mutableStateOf(false) }

    if (showAnimation) {
        LaunchedEffect(Unit) {
            isVisible = true
        }
    }

    val animationBlur by animateFloatAsState(
        if (showAnimation) 0f else 5f, tween(
            durationMillis = 460, delayMillis = 200, easing = EaseOut
        )
    )
    val animateAlpha by animateFloatAsState(
        if (showAnimation) 1f else 0f, tween(
            durationMillis = 460, delayMillis = 200, easing = EaseOut
        ), label = "alpha"
    )

    Text(
        text = "Real-Time Insights",
        fontSize = 14.sp,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .alpha(if (isVisible) animateAlpha else 1f)
            .blur(if (isVisible) animationBlur.dp else 0.dp)
    )

}

@Composable
private fun AnimatingTextSubHeading(
    showAnimation: Boolean,
) {
    var isVisible by remember { mutableStateOf(false) }
    val color = MaterialTheme.colorScheme.onPrimary.copy(.8f)

    if (showAnimation) {
        LaunchedEffect(Unit) {
            isVisible = true
        }
    }

    val animationBlur by animateFloatAsState(
        if (showAnimation) 0f else 5f, tween(
            durationMillis = 600, delayMillis = 400, easing = EaseOut
        )
    )
    val animateAlpha by animateFloatAsState(
        if (showAnimation) 1f else 0f, tween(
            durationMillis = 600, delayMillis = 400, easing = EaseOut
        ), label = "alpha"
    )

    Text(
        text = "Track and continuously optimize\nperformance with real-time analytics.",
        fontSize = 12.sp,
        style = MaterialTheme.typography.titleSmall,
        color = color,
        lineHeight = 14.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .alpha(if (isVisible) animateAlpha else 1f)
            .blur(if (isVisible) animationBlur.dp else 0.dp)
    )

}

fun linePath(size: Size): Path {
    val path = Path()

    path.moveTo(0f, size.height * 5f)


    path.cubicTo(
        size.width * 0.1f,
        size.height * 0.45f,
        size.width * 0.3f,
        size.height * 0.25f,
        size.width * 0.45f,
        size.height * 0.24f
    )


    path.cubicTo(
        size.width * 0.58f,
        size.height * 0.24f,
        size.width * 0.65f,
        size.height * 0.18f,
        size.width * 0.75f,
        size.height * 0.05f
    )

    return path
}

@ThemePreview
@Composable
private fun CardDesignPreview() {
    PortfolioTheme {
        CardDesign()
    }
}
