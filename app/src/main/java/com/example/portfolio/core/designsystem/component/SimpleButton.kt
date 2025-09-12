package com.example.portfolio.core.designsystem.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.portfolio.core.designsystem.icon.ApplicationIcons

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SimpleButton(
    onClick: () -> Unit,
    icon: ImageVector = ApplicationIcons.Back,
    resourceIcon: Int? = null
) {
    FilledTonalIconButton(
        onClick = onClick,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = Color.Red,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = Color.Red
        ),
        shapes = IconButtonShapes(
            shape = CircleShape,
            pressedShape = RoundedCornerShape(10.dp)
        )
    ) {
        if (resourceIcon != null) {
            Icon(painterResource(resourceIcon), "FilledTonalButtonIcon")
        } else {
            Icon(icon, "FilledTonalButtonIcon")
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SimpleLinkButton(
    icon: ImageVector = ApplicationIcons.Back,
    resourceIcon: Int? = null
) {
    FilledTonalIconButton(
        onClick = { },
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = Color.Red,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = Color.Red
        ),
        shapes = IconButtonShapes(
            shape = CircleShape,
            pressedShape = RoundedCornerShape(10.dp)
        )
    ) {
        if (resourceIcon != null) {
            Icon(painterResource(resourceIcon), "FilledTonalButtonIcon")
        } else {
            Icon(icon, "FilledTonalButtonIcon")
        }
    }
}


@Composable
fun SimpleElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    height: Dp = 256.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = RoundedCornerShape(12.dp),
    borderWidth: Dp = (0.5).dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    padding: Dp = (4.5).dp,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }

    val dullAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0f else 50f,
        animationSpec = tween(durationMillis = 250),
        label = "DullAnimation"
    )

    val clickableModifier = if (onClick != null) {
        modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    } else {
        modifier
    }

    /*    val clickableModifier = if (onClick != null) {
            modifier.pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        delay(200)
                        isPressed = true
                        try {
                            tryAwaitRelease()
                        } finally {
                            isPressed = false
                            onClick()
                        }
                    }
                )
            }
        } else {
            modifier
        }*/

    OutlinedCard(
        modifier = clickableModifier
            .height(height)
            .fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.elevatedCardColors(backgroundColor),
        border = BorderStroke(borderWidth, borderColor),
    ) {
        Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}
