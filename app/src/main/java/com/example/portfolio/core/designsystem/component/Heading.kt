package com.example.portfolio.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.portfolio.core.designsystem.util.DeviceConfiguration

@Composable
fun Heading(
    text: String,
    style: TextStyle = MaterialTheme.typography.displayLarge,
    bottomPadding: Dp = 14.dp,
    deviceConfiguration: DeviceConfiguration = DeviceConfiguration.Horizontal
) {
    val topPadding = when (deviceConfiguration) {
        DeviceConfiguration.Vertical -> 24.dp
        else -> 48.dp
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, bottom = bottomPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text,
            style = style,
            softWrap = false
        )
    }
}