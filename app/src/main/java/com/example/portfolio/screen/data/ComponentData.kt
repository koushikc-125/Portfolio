package com.example.portfolio.screen.data

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

data class ComponentData(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val content: @Composable () -> Unit
)
