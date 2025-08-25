package com.example.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.portfolio.core.designsystem.theme.PortfolioTheme
import com.example.portfolio.screen.ui.DetailScreen
import com.example.portfolio.screen.ui.HomeScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioTheme {
                PortfolioApp()
            }
        }
    }
}

@Serializable
data object HomeScreenList : NavKey

@Serializable
data class Detail(val id: Int) : NavKey

@Composable
fun PortfolioApp() {
    val backStack = rememberNavBackStack(HomeScreenList)

    NavDisplay(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is HomeScreenList -> {
                    NavEntry(
                        key = key,
                    ) {
                        HomeScreen(
                            onClick = { componentInfo ->
                                backStack.add(Detail(componentInfo.id))
                            }
                        )
                    }
                }

                is Detail -> {
                    NavEntry(
                        key = key,
                    ) {
                        DetailScreen(
                            currentComponentInfoId = key.id,
                            onBack = {
                                if (backStack.isNotEmpty()) {
                                    backStack.removeLastOrNull()
                                }
                            }
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
    )
}
