package com.example.portfolio.core.designsystem.util

import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class DeviceConfiguration() {
    Vertical, // (640px and up)
    Horizontal;  // (768px and up)

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            val widthClass = windowSizeClass.windowWidthSizeClass
            return when (widthClass) {
                WindowWidthSizeClass.COMPACT -> Vertical
                WindowWidthSizeClass.MEDIUM -> Horizontal
                WindowWidthSizeClass.EXPANDED -> Horizontal
                else -> Vertical
            }
        }
    }
}
