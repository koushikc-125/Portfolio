package com.example.portfolio.screen.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.portfolio.components.CardDesign
import com.example.portfolio.components.DeleteButton
import com.example.portfolio.components.GradientIcon
import com.example.portfolio.core.util.Soon

object ComponentInfo {

    val titles = listOf(
        "Coming Soon",
        "Minimal Card Design",
        "The Finder Icon",
        "Coming Soon"
    )

    val descriptions = listOf(
        "Coming Soon",
        "A minimal card design with path animation.",
        "Finder icon draw with path",
        "Coming Soon"
    )
    val time = listOf(
        "soon",
        "27.8.25",
        "6.9.25",
        "soon"
    )

    val content: List<@Composable () -> Unit> = listOf(
        { DeleteButton() },
        { CardDesign() },
        { GradientIcon() },
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