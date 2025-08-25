package com.example.portfolio.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified

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