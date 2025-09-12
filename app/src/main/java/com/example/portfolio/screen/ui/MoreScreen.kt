@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.portfolio.screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.portfolio.core.designsystem.component.Container
import com.example.portfolio.core.designsystem.component.SimpleButton
import com.example.portfolio.core.designsystem.component.ThemePreview
import com.example.portfolio.core.designsystem.theme.PortfolioTheme
import com.example.portfolio.core.designsystem.util.DeviceConfiguration
import com.example.portfolio.core.ui.MoreComponentsGrid
import com.example.portfolio.screen.data.ComponentData

@Composable
fun MoreScreen(
    onItemClick: (ComponentData) -> Unit,
    onBack: () -> Unit,
) {
    val horizontalPadding = 24.dp
    val bottomPadding = 28.dp
    val backgroundColor = MaterialTheme.colorScheme.surface
    val state = rememberLazyListState()

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    windowInsets = WindowInsets(top = 50.dp),
                    modifier = Modifier
                        .width(712.dp),
                    title = {},
                    navigationIcon = { SimpleButton(onBack = onBack) }
                )
            }
        }
    ) { paddingValues ->
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .width(692.dp)
                    .fillMaxHeight(.08f)
//                    .background(Color.Red)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(backgroundColor, Color.Transparent)
                            ),
                            blendMode = BlendMode.SrcOver,
                        )
                    }
            )
        }

        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding)
            //    .windowInsetsPadding(WindowInsets.statusBars)
            ,
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Container(
                    deviceConfiguration
                ) {
                    MoreComponentsGrid(
                        onClick = onItemClick
                    )
                }
            }
        }
    }
}

@ThemePreview
@Composable
private fun MoreScreenPreview() {
    PortfolioTheme {
        MoreScreen(
            onItemClick = {},
            onBack = {}
        )
    }
}
