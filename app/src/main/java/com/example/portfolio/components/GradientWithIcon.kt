package com.example.portfolio.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.portfolio.core.designsystem.theme.GradientDark
import com.example.portfolio.core.designsystem.theme.GradientLight
import com.example.portfolio.core.designsystem.theme.PathDark
import com.example.portfolio.core.designsystem.theme.PathLight

@Composable
fun GradientIcon(
    showAnimation: Boolean = false
) {

    val getFinderColors: FinderColors =
        if (isSystemInDarkTheme()) DarkFinderColors else LightFinderColors

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                onDrawBehind {
                    val gradientCenterX = size.width / 2.2f
                    val gradientCenterY = size.height / 2f
                    val radius =
                        minOf(size.width, size.height) * 0.2f // Smaller radius for the effect

                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                getFinderColors.gradientColor.copy(alpha = 0.6f),
                                Color.Transparent // Transparent edges
                            ), center = Offset(gradientCenterX, gradientCenterY), radius = radius
                        )
                    )

                    val centerX = size.width / 2f
                    val centerY = size.height / 2f

                    drawContext.canvas.save()
                    drawContext.canvas.translate(centerX, centerY)
//M 0 -65 C -14 -52 -20 -15 -20 -5 C -20 5 -20 15 -10 15 C 5 15 5 15 5 35 L 5 35 C 5 49 5 65 13 81
                    val path = Path().apply {
//body
                        //M 0 -65
                        moveTo(0f, -65f)

                        //C -14 -52 -20 -15 -20 -5
                        cubicTo(
                            -14f, -52f, -20f, -15f, -20f, -5f
                        )

                        //C -20 5 -20 15 -10 15
                        cubicTo(
                            -20f, 5f, -20f, 15f, -10f, 15f
                        )

                        //C 5 15 5 15 5 35
                        cubicTo(
                            5f, 15f, 5f, 15f, 5f, 35f
                        )

                        //L 5 35
                        lineTo(5f, 35f)

                        //C 5 49 5 65 13 81
                        cubicTo(
                            5f, 49f, 5f, 65f, 13f, 81f
                        )
//eyes
                        //M -42 -15
                        moveTo(-42f, -15f)

                        //L -42 -33
                        lineTo(-42f, -28f)

                        //M 12 -15
                        moveTo(20f, -15f)

                        //L 12 -33
                        lineTo(20f, -28f)
//smile
                        //M -40 48
                        moveTo(-50f, 40f)

                        //Q -6 54 28 48
                        quadraticTo(
                            -6f, 60f, 38f, 40f
                        )
                    }

                    drawPath(
                        path = path,
                        color = getFinderColors.pathColor,
                        style = Stroke(
                            width = 12f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )

                    drawContext.canvas.restore()
                }
            }
    )
}

data class FinderColors(
    val pathColor: Color, val gradientColor: Color
)

val LightFinderColors = FinderColors(
    pathColor = PathLight, gradientColor = GradientLight
)

val DarkFinderColors = FinderColors(
    pathColor = PathDark, gradientColor = GradientDark
)
