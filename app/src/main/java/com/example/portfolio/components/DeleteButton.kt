package com.example.portfolio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DeleteButton(
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

private @Composable
fun SimpleButton(
    showAnimation: Boolean,
) {
    var isPressed by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    val animatedScale by animateFloatAsState(
        if (isPressed) .84f else 1f,
        tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "AnimatedScaleF"
    )

    val animatedWidth by animateFloatAsState(
        if (isPressed) 1f else 0f,
        tween(
            durationMillis = 800,
            easing = FastOutLinearInEasing
        ),
        label = "AnimatedWidth"
    )

    LaunchedEffect(animatedWidth) {
        if (animatedWidth >= 0.99) {
            openAlertDialog = true
        }
    }

    AnimatedVisibility(openAlertDialog) {
        AlertDialogExample(
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = {
                openAlertDialog = false
                println("Confirmation registered") // Add logic here to handle confirmation.
            },
            dialogTitle = "Alert dialog example",
            dialogText = "This is an example of an alert dialog with buttons.",
            icon = Icons.Default.Info
        )
    }

    Box(
        Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.2f)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (showAnimation) {
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
            "Hold to Delete",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .scale(animatedScale)
        )
        Box(
            Modifier
                .fillMaxWidth(animatedWidth)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        )
        Text(
            "Hold to Delete",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxHeight(.7f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .drawWithContent {
                    drawContent()
                }
                .scale(animatedScale)
                .drawWithContent {
                    val overlayWidth = size.width * animatedWidth
                    clipRect(right = overlayWidth) {
                        this@drawWithContent.drawContent()
                    }
                },
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}
