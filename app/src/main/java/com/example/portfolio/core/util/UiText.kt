package com.example.portfolio.core.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResourceId(
        @StringRes val id: Int,
        vararg val args: Any
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(id = id, formatArgs = args)
        }
    }

    fun asStringNoCOMP(): String {
        return when (this) {
            is DynamicString -> value
            is StringResourceId -> "TODO()"
        }
    }
}
