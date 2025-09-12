package com.example.portfolio.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SquishButton(
    showAnimation: Boolean = false,
) {
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SimpleButton(showAnimation)
    }
}

@Composable
private fun SimpleButton(
    showAnimation: Boolean,
) {
    val hapticFeedback = LocalHapticFeedback.current
    var isPressed by remember { mutableStateOf(false) }

    val scaleAnimation by animateFloatAsState(
        if (isPressed) .98f else 1f,
        tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "AnimatedScaleF"
    )

    val widthAnimation by animateFloatAsState(
        if (isPressed) 1f else 0f,
        tween(
            durationMillis = 800,
            easing = FastOutLinearInEasing
        ),
        label = "AnimatedWidth"
    )

    val shadowAnimation by animateDpAsState(
        if(isPressed) 0.dp else 1.dp,
        tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "AnimatedShadow"
    )

    Box(
        Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.2f)
            .shadow(shadowAnimation, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
            .border(.5.dp, MaterialTheme.colorScheme.outline.copy(.8f), RoundedCornerShape(24.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (showAnimation) {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                            isPressed = true
                            try {
                                tryAwaitRelease()
                            } finally {
                                isPressed = false
                            }
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Hold to Squish",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .scale(scaleAnimation)
        )
        Box(
            Modifier
                .fillMaxWidth(widthAnimation)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .background(MaterialTheme.colorScheme.secondary)
        )
        Text(
            "Hold to Squish",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .drawWithContent {
                    drawContent()
                }
                .scale(scaleAnimation)
                .drawWithContent {
                    val masksWidth = size.width * widthAnimation
                    clipRect(right = masksWidth) {
                        this@drawWithContent.drawContent()
                    }
                },
            textAlign = TextAlign.Center,
        )
    }
}
