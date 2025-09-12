package com.example.portfolio.screen.data

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

data class ComponentData(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val gitLinks: String,
    val isFullWidth: Boolean,
    val content: @Composable () -> Unit
)
