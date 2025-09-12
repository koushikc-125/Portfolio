package com.example.portfolio.screen.data

import androidx.compose.runtime.Composable
import com.example.portfolio.components.CardDesign
import com.example.portfolio.components.SquishButton
import com.example.portfolio.components.GradientIcon
import com.example.portfolio.core.util.Soon
import com.example.portfolio.core.util.gitUrl

object ComponentInfo {

    val titles = listOf(
        "Squish Button",
        "The Finder Icon",
        "Minimal Card Design",
    )

    val descriptions = listOf(
        "A interactive button that compresses upon user press, featuring a progressive overlay animation that responds dynamically to pressure, creating a visually engaging masking effect.",
        "Finder icon draw with path",
        "A minimal card design with path animation.",
    )
    val time = listOf(
        "13.9.25",
        "6.9.25",
        "27.8.25",
    )

    val content: List<@Composable () -> Unit> = listOf(
        { SquishButton() },
        { GradientIcon() },
        { CardDesign() },
    )

    val gitLinks = listOf(
        "$gitUrl/components/SquishButton.kt",
        "$gitUrl/components/GradientWithIcon.kt",
        "$gitUrl/components/CardDesign.kt",
    )
    val isFullWidth = listOf(
        false,
        false,
        true,
    )

    val componentsInfo = titles.mapIndexed { idx, title ->
        ComponentData(
            id = idx,
            title = title,
            description = descriptions[idx],
            time = time[idx],
            gitLinks = gitLinks[idx],
            isFullWidth = isFullWidth[idx],
            content = content[idx]
        )
    }
}