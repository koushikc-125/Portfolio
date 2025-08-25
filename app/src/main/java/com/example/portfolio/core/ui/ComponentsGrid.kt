package com.example.portfolio.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfolio.core.designsystem.component.SubHeading
import com.example.portfolio.core.designsystem.util.DeviceConfiguration
import com.example.portfolio.screen.data.ComponentInfo.componentsInfo
import com.example.portfolio.screen.data.ComponentData
import kotlin.collections.chunked

@Composable
fun ComponentsGrid(
    deviceConfiguration: DeviceConfiguration = DeviceConfiguration.Horizontal,
    onClick: (ComponentData) -> Unit
) {
    val itemNumber = when(deviceConfiguration) {
        DeviceConfiguration.Vertical -> 1
        else -> 2
    }
    val chunkedItems = componentsInfo.chunked(itemNumber)
    val topPadding = 84.dp

    Box(
        modifier = Modifier
            .padding(top = topPadding),
    ) {
        Column {
            SubHeading(
                text = "Works",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                bottomPadding = 24.dp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                chunkedItems.forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { item ->
                            Box(modifier = Modifier.weight(1f)) {
                                SimpleCard(
                                    onClick = { onClick(item) },
                                    title = item.title,
                                    time = item.time,
                                    content = item.content
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}