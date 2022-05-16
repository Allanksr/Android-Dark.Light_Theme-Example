package allanksr.com.live_theme.presentation.nav_graph

import allanksr.com.live_theme.presentation.MainScreen
import allanksr.com.live_theme.presentation.OtherScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraphController(
    text: String,
    liveTheme: MutableState<Boolean>,
    storeThemeState: (key: String, value: Boolean) -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route
    ) {
        composable(
            route = Route.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
                text = text,
                liveTheme = liveTheme,
                storeThemeState = { key, value ->
                    storeThemeState(key, value)
                }
            )
        }
        composable(
            route = Route.OtherScreen.route
        ) {
            OtherScreen(
                liveTheme = liveTheme,
                storeThemeState = { key, value ->
                    storeThemeState(key, value)
                }
            )
        }
    }
}