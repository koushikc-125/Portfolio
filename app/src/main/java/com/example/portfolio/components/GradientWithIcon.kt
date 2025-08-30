package com.example.portfolio.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun GradientIcon(
    showAnimation: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                onDrawBehind {
                    val centerX = size.width / 2f
                    val centerY = size.height / 2f
                    // Scale factor to make the path bigger and visible
                    val scale = minOf(size.width, size.height) / 100f  // Increased from 100f to 30f
                    val path = Path().apply {
                        // M63 12 - Move to point (63, 12)
                        moveTo(63f * scale, 12f * scale)

                        // V10 - Vertical line to y=10 (equivalent to lineTo(63, 10))
                        lineTo(63f * scale, 10f * scale)

                        // M54.5 12 - Move to new point (54.5, 12)
                        moveTo(54.5f * scale, 12f * scale)

                        // V10 - Vertical line to y=10 (equivalent to lineTo(54.5, 10))
                        lineTo(54.5f * scale, 10f * scale)

                        // M64 20 - Move to new point (64, 20)
                        moveTo(64f * scale, 20f * scale)

                        // C59 21 59 21 54 20 - Cubic bezier curve to (54, 20)
                        cubicTo(
                            x1 = 59f * scale, y1 = 21f * scale,
                            x2 = 59f * scale, y2 = 21f * scale,
                            x3 = 54f * scale, y3 = 20f * scale
                        )

                        // M61 24 - Move to new point (61, 24)
                        moveTo(61f * scale, 24f * scale)

                        // C60 21 60 16 60 17 60 17 60 16 59 16 - Cubic bezier curve to (59, 16)
                        cubicTo(
                            x1 = 60f * scale, y1 = 21f * scale,
                            x2 = 60f * scale, y2 = 16f * scale,
                            x3 = 60f * scale, y3 = 17f * scale
                        )

                        // Continue the cubic bezier: 60 17 60 16 59 16
                        cubicTo(
                            x1 = 60f * scale, y1 = 17f * scale,
                            x2 = 60f * scale, y2 = 16f * scale,
                            x3 = 59f * scale, y3 = 16f * scale
                        )

                        // H58 - Horizontal line to x=58 (equivalent to lineTo(58, 16))
                        lineTo(58f * scale, 16f * scale)

                        // S57 16 57 15 - Smooth cubic curve to (57, 15)
                        // S creates a smooth cubic bezier where first control point is reflection of previous
                        // Since previous curve ended at (59,16) with control point (60,16),
                        // the reflected control point would be (58,16)
                        cubicTo(
                            x1 = 58f * scale, y1 = 16f * scale,  // Reflected control point
                            x2 = 57f * scale, y2 = 16f * scale,  // Second control point
                            x3 = 57f * scale, y3 = 15f * scale   // End point
                        )

                        // C57 11 59 7 60 5 - Final cubic bezier curve to (60, 5)
                        cubicTo(
                            x1 = 57f * scale, y1 = 11f * scale,
                            x2 = 59f * scale, y2 = 7f * scale,
                            x3 = 60f * scale, y3 = 5f * scale
                        )

                        // Translate path to center (adjust these values based on your actual bounds)
                        translate(
                            Offset(
                                centerX - (59f * scale),     // Approximate center X of your path
                                centerY - (14.5f * scale)    // Approximate center Y of your path
                            )
                        )
                    }

                    drawPath(
                        path = path,
                        color = Color(0xFF07223A),
                        style = Stroke(
                            width = 5.dp.toPx(),
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }
            .drawWithContent {
                drawContent()

                // Draw circular blue gradient background
                val centerX = size.width / 2.2f
                val centerY = size.height / 2f
                val radius = minOf(size.width, size.height) * 0.2f // Smaller radius for the effect

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF6B9FFF).copy(alpha = 0.6f), // Light blue center
                            Color(0xFF4A7BFF).copy(alpha = 0.4f), // Medium blue
                            Color(0xFF4A7BFF).copy(alpha = 0.1f), // Medium blue
                            Color.Transparent // Transparent edges
                        ),
                        center = Offset(centerX, centerY),
                        radius = radius
                    ),
                    radius = radius,
                    center = Offset(centerX, centerY)
                )
            },
        contentAlignment = Alignment.Center
    ) {

    }
}