package com.example.portfolio.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.portfolio.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun typography(
    font: FontFamily = FontFamily(Font(R.font.inter_medium)),
): Typography {

    return Typography(
        displayLarge = TextStyle(
            fontSize = 24.sp,
            fontFamily = font,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-.025).em,
            lineHeight = 1.45.sp
        ),
        bodyMedium = TextStyle(
            fontSize = 16.sp,
            fontFamily = font,
            letterSpacing = (-.015).em,
            lineHeight = 1.5.em,
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            fontFamily = font,
            fontWeight = FontWeight.Medium,
            lineHeight = 1.625.em
        ),
        labelMedium = TextStyle(
            fontSize = 16.sp,
            fontFamily = font,
            lineHeight = 1.45.sp
        )
    )
}