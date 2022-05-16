package allanksr.com.live_theme.presentation

import allanksr.com.live_theme.presentation.nav_graph.Route
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    text: String,
    liveTheme: MutableState<Boolean>,
    storeThemeState: (key: String, value: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    Button(
                        onClick = {
                            liveTheme.value = !liveTheme.value
                            storeThemeState("theme_key", liveTheme.value)
                        },
                        content = {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 5.dp,
                                        top = 5.dp,
                                        end = 5.dp,
                                        bottom = 5.dp,
                                    ),
                                text = text,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    )
                }
            )

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    Button(
                        onClick = {
                            navController.navigate(Route.OtherScreen.route)
                        },
                        content = {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 5.dp,
                                        top = 5.dp,
                                        end = 5.dp,
                                        bottom = 5.dp,
                                    ),
                                text = "Other Screen",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    )
                }
            )
        }
    )
}
