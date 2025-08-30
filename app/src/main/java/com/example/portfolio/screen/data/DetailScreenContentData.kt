package com.example.portfolio.screen.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.portfolio.components.CardDesign
import com.example.portfolio.core.util.Soon

val detailScreenContentData: List<@Composable () -> Unit> = listOf(
    { CardDesign(showAnimation = true) },
    { Soon() }
)
