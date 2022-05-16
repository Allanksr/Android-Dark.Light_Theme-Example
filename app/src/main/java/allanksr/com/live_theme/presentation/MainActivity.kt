package allanksr.com.live_theme.presentation

import allanksr.com.live_theme.presentation.nav_graph.NavGraphController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import allanksr.com.live_theme.presentation.theme.LiveThemeExampleTheme
import allanksr.com.live_theme.util.changeStatusBarColor
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    //For larger projects, use the Dependency Injection design pattern.
    // like this https://github.com/Allanksr/Android-Data.Store.Proto-Example/tree/master/app/src/main/java/allanksr/com/datastore_example/di
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStoreName")
    }

    private fun storeThemeState(key: String, value: Boolean) {
        val keyForValue = booleanPreferencesKey(key)
        lifecycleScope.launch {
            dataStore.edit { settings ->
                settings[keyForValue] = value
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val liveTheme: MutableState<Boolean> = mutableStateOf(false)
        lifecycleScope.launch {
            val themeKey: Boolean = dataStore.data
                .map { preferences ->
                    preferences[booleanPreferencesKey("theme_key")] ?: false
                }.first()
            liveTheme.value = themeKey
            changeStatusBarColor(themeKey)
            setContent {
                LiveThemeExampleTheme(
                    darkTheme = liveTheme.value,
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background,
                            content = {
                                NavGraphController(
                                    text = "Is black theme: ${liveTheme.value}",
                                    liveTheme = liveTheme,
                                    storeThemeState = { key, value ->
                                        storeThemeState(key, value)
                                    }
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
