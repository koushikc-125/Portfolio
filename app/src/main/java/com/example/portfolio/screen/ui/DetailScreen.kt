package com.example.portfolio.screen.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.portfolio.core.designsystem.component.Container
import com.example.portfolio.core.designsystem.component.SimpleButton
import com.example.portfolio.core.designsystem.component.SimpleElevatedCard
import com.example.portfolio.core.designsystem.component.SubHeading
import com.example.portfolio.core.designsystem.component.ThemePreview
import com.example.portfolio.core.designsystem.icon.ApplicationIcons
import com.example.portfolio.core.designsystem.theme.PortfolioTheme
import com.example.portfolio.core.designsystem.util.DeviceConfiguration
import com.example.portfolio.core.util.getComponentInfoById
import com.example.portfolio.screen.data.ComponentData
import com.example.portfolio.screen.data.detailScreenContentData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    currentComponentInfoId: Int,
    onBack: () -> Unit
) {
    val horizontalPadding = 24.dp
    val topHorizontalPadding = 12.dp
    val topPadding = 48.dp
    val currentComponentInfo = getComponentInfoById(currentComponentInfoId)
    val previousComponentInfoId = null
    val nextComponentInfoId = null
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.surface

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                    modifier = Modifier
                        .width(712.dp),
                    windowInsets = WindowInsets(top = 50.dp),
                    navigationIcon = { SimpleButton(onClick = onBack) },
                    title = {},
                    actions = {
                        SimpleButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    "https://${currentComponentInfo.gitLinks}".toUri()
                                )
                                context.startActivity(intent)
                            },
                            resourceIcon = ApplicationIcons.Link,

                            )
                    },
                )
            }
        }
    ) { paddingValues ->
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
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
                    DetailScreenContent(currentComponentInfo)
                }
            }
        }
    }
}

@Composable
fun DetailScreenContent(componentData: ComponentData) {
    SubHeading(
        text = componentData.title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        bottomPadding = 12.dp
    )
    SubHeading(
        text = componentData.description,
        bottomPadding = 0.dp
    )
    SimpleElevatedCard(
        onClick = {},
        modifier = Modifier
            .padding(vertical = 32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            detailScreenContentData[componentData.id]()
        }
    }
}

@ThemePreview
@Composable
private fun DetailScreenPreview() {
    PortfolioTheme {
        DetailScreen(
            currentComponentInfoId = 1,
            onBack = {},
        )
    }
}
