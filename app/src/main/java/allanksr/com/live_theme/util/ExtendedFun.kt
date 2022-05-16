package allanksr.com.live_theme.util

import allanksr.com.live_theme.R
import android.app.Activity
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.changeStatusBarColor(isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = if(isLight) {
        ContextCompat.getColor(this, R.color.dark_gray)
    } else {
        ContextCompat.getColor(this, R.color.dark_green)
    }
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
}