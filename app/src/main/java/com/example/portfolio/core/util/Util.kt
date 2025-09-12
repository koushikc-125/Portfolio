package com.example.portfolio.core.util

import com.example.portfolio.screen.data.ComponentInfo
import com.example.portfolio.screen.data.ComponentData

fun getComponentInfoById(id: Int): ComponentData {
    return ComponentInfo.componentsInfo.find { it.id == id }
        ?: throw IllegalArgumentException("Component with id $id not found")
}

const val gitUrl = "github.com/koushikc-125/Portfolio/blob/main/app/src/main/java/com/example/portfolio"
