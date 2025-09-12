package com.example.portfolio.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.portfolio.core.designsystem.component.SimpleElevatedCard
import com.example.portfolio.core.designsystem.component.SubHeading
import com.example.portfolio.core.designsystem.component.ThemePreview
import com.example.portfolio.core.designsystem.theme.PortfolioTheme
import com.example.portfolio.screen.data.ComponentInfo

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SimpleCard(
    onClick: () -> Unit,
    title: String? = null,
    time: String? = null,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    content: @Composable () -> Unit,
) {
    SimpleElevatedCard(
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clickable{ onClick() }
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = .5.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (title != null && time != null) {
                    SubHeading(
                        text = title,
                        style = style,
                        color = MaterialTheme.colorScheme.onPrimary,
                        bottomPadding = 4.dp
                    )
                    SubHeading(
                        text = time,
                        style = style,
                        color = LocalContentColor.current.copy(0.8f),
                        bottomPadding = 2.dp
                    )
                }
            }
        }
    }
}


@ThemePreview
@Composable
private fun SimpleCardPreview() {
    val content = ComponentInfo
    PortfolioTheme {
        SimpleCard(
            onClick = {},
            time = content.time[1],
            title = content.titles[1],
            content = content.content[1]
        )
    }
}
