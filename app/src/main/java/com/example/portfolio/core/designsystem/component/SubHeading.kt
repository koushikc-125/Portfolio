package com.example.portfolio.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SubHeading(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    contentAlignment: Alignment = Alignment.CenterStart,
    bottomPadding: Dp = 24.dp,
) {

    var resizedTestStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding),
        contentAlignment = contentAlignment
    ) {
        Text(
            text,
            modifier = Modifier.drawWithContent {
                if (shouldDraw) {
                    drawContent()
                }
            },
            style = style,
            color = color,
            softWrap = true,
            onTextLayout = { result ->
                if (result.didOverflowHeight) {
                    resizedTestStyle = resizedTestStyle.copy(
                        fontSize = resizedTestStyle.fontSize * 0.95
                    )
                } else {
                    shouldDraw = true
                }
            }
        )
    }
}

@Composable
fun SubHeadingWithIcon(
    text: String,
    icon: ImageVector?,
    onIconClick: () -> Unit,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    contentAlignment: Alignment = Alignment.CenterStart,
    bottomPadding: Dp = 24.dp,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var resizedTestStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding),
        contentAlignment = contentAlignment
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text,
                modifier = Modifier.drawWithContent {
                    if (shouldDraw) {
                        drawContent()
                    }
                },
                style = style,
                color = color,
                softWrap = true,
                onTextLayout = { result ->
                    if (result.didOverflowHeight) {
                        resizedTestStyle = resizedTestStyle.copy(
                            fontSize = resizedTestStyle.fontSize * 0.95
                        )
                    } else {
                        shouldDraw = true
                    }
                }
            )
            if (icon != null) {

                Icon(
                    icon,
                    "Forward",
                    Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onIconClick
                        )
                )

            }
        }
    }
}
