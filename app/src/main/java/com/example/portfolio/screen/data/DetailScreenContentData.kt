package com.example.portfolio.screen.data

import androidx.compose.runtime.Composable
import com.example.portfolio.components.CardDesign
import com.example.portfolio.components.DeleteButton
import com.example.portfolio.components.GradientIcon
import com.example.portfolio.core.util.Soon

val detailScreenContentData: List<@Composable () -> Unit> = listOf(
    { DeleteButton(showAnimation = true) },
    { CardDesign(showAnimation = true) },
    { GradientIcon() },
    { Soon() }
)
