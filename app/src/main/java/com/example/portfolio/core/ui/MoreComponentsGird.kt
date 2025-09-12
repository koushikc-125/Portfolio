package com.example.portfolio.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfolio.screen.data.ComponentData
import com.example.portfolio.screen.data.ComponentInfo.componentsInfo

@Composable
fun MoreComponentsGrid(
    onClick: (ComponentData) -> Unit,
) {
    val topPadding = 84.dp

    Box(
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var currentRowItems = mutableListOf<ComponentData>()

            componentsInfo.forEach { rowItems ->
                when {
                    rowItems.isFullWidth -> {
                        if (currentRowItems.isNotEmpty()) {
                            RenderRow(currentRowItems, onClick)
                            currentRowItems = mutableListOf()
                        }

                        RenderFullWidthItem(rowItems, onClick)
                    }

                    else -> {
                        currentRowItems.add(rowItems)

                        if (currentRowItems.size == 2) {
                            RenderRow(currentRowItems, onClick)
                            currentRowItems = mutableListOf()
                        }
                    }
                }
            }

            // Render any remaining items in the last row
            if (currentRowItems.isNotEmpty()) {
                RenderRow(currentRowItems, onClick)
            }
        }
    }
}

@Composable
private fun RenderRow(
    items: List<ComponentData>,
    onClick: (ComponentData) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            Box(modifier = Modifier.weight(1f)) {
                SimpleCard(
                    onClick = { onClick(item) },
                    title = item.title,
                    time = item.time,
                    content = item.content
                )
            }
        }
        // If only one item in row, add spacer to maintain consistent sizing
        if (items.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun RenderFullWidthItem(
    item: ComponentData,
    onClick: (ComponentData) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        SimpleCard(
            onClick = { onClick(item) },
            title = item.title,
            time = item.time,
            content = item.content
        )
    }
}
