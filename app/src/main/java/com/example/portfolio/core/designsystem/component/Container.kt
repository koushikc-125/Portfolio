package com.example.portfolio.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.portfolio.core.designsystem.util.DeviceConfiguration

@Composable
fun Container(
    deviceConfiguration: DeviceConfiguration,
    modifier: Modifier = Modifier,
    smVerticalPadding: Dp = 48.dp,
    defaultVerticalPadding: Dp = 64.dp,
    maxWidth: Dp = 692.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .width(maxWidth)
            .wrapContentHeight()
            .padding(
                top = when(deviceConfiguration) {
                    DeviceConfiguration.Vertical -> smVerticalPadding
                    else -> defaultVerticalPadding
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}