package com.example.portfolio.screen.data

import androidx.compose.runtime.Composable
import com.example.portfolio.core.util.Soon

object ComponentInfo {

    val titles = listOf(
        "Coming Soon"
    )

    val descriptions = listOf(
        "Coming Soon"
    )
    val time = listOf(
        "soon"
    )

    val content: List<@Composable () -> Unit> = listOf(
        { Soon() }
    )
    val componentsInfo = titles.mapIndexed { idx, title ->
        ComponentData(
            id = idx,
            title = title,
            description = descriptions[idx],
            time = time[idx],
            content = content[idx]
        )
    }
}