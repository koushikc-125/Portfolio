package com.example.portfolio.screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.portfolio.R
import com.example.portfolio.core.designsystem.component.Container
import com.example.portfolio.core.designsystem.component.Heading
import com.example.portfolio.core.designsystem.component.SimpleButtonGroup
import com.example.portfolio.core.designsystem.component.SubHeading
import com.example.portfolio.core.designsystem.component.ThemePreview
import com.example.portfolio.core.designsystem.theme.PortfolioTheme
import com.example.portfolio.core.designsystem.util.DeviceConfiguration
import com.example.portfolio.core.ui.ComponentsGrid
import com.example.portfolio.core.util.UiText
import com.example.portfolio.screen.data.ComponentData

@Composable
fun HomeScreen(
    onClick: (ComponentData) -> Unit = {}
) {
    val options = listOf("X", "Mail", "GitHub")
    val optionsLink =
        listOf("x.com/koushikc125", "koushikc513@gmail.com", "github.com/koushikc-125")
    val horizontalPadding = 24.dp
    val bottomPadding = 28.dp
    val backgroundColor = MaterialTheme.colorScheme.surface
    val state = rememberLazyListState()

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface,
    ) { paddingValues ->
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f), contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .width(692.dp)
                    .fillMaxHeight(.08f)
                    //.background(Color.Red)
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
                    deviceConfiguration = deviceConfiguration,
                ) {
                    HomeScreenContent(
                        onClick = onClick,
                        options = options,
                        optionsLink = optionsLink,
                        bottomPadding = bottomPadding,
                        deviceConfiguration = deviceConfiguration,
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    onClick: (ComponentData) -> Unit,
    options: List<String>,
    optionsLink: List<String>,
    bottomPadding: Dp,
    deviceConfiguration: DeviceConfiguration
) {
    Heading(
        text = UiText.StringResourceId(R.string.heading).asString(),
        deviceConfiguration = deviceConfiguration
    )
    SubHeading(
        text = UiText.StringResourceId(R.string.subheading_1).asString(),
        bottomPadding = 28.dp
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleButtonGroup(
            options = options,
            optionLink = optionsLink,
            modifier = Modifier.graphicsLayer(rotationZ = -50f)
        )
    }
    ComponentsGrid(
        deviceConfiguration = deviceConfiguration, onClick = onClick
    )
    Spacer(modifier = Modifier.height(bottomPadding))
}

@ThemePreview
@Composable
private fun HomeScreenPreview() {
    PortfolioTheme {
        HomeScreen({})
    }
}

