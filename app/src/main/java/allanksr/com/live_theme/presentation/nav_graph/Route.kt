package allanksr.com.live_theme.presentation.nav_graph

sealed class Route(val route: String) {
    object MainScreen : Route("main_screen")
    object OtherScreen : Route("other_screen")
}