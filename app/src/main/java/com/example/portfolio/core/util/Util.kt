package com.example.portfolio.core.util

import com.example.portfolio.screen.data.ComponentInfo
import com.example.portfolio.screen.data.ComponentData

fun getComponentInfoById(id: Int): ComponentData {
    return ComponentInfo.componentsInfo.find { it.id == id }
        ?: throw IllegalArgumentException("Component with id $id not found")
}