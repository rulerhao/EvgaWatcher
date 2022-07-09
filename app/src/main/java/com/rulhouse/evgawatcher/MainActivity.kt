package com.rulhouse.evgawatcher

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rulhouse.evgawatcher.methods.ActivitySetting
import com.rulhouse.evgawatcher.presentation.screen.MainScreen
import com.rulhouse.evgawatcher.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var notification: NotificationUseCase
//
//    @Inject
//    lateinit var notificationUseCase:NotificationUseCase

//    @Inject
//    lateinit var workManagerUseCases: WorkManagerUseCases

    val activitySetting = ActivitySetting()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()
//        activitySetting.hindSystemBar(this)
//
//        activitySetting.addSystemUIListener(this)

        setContent {
            AppTheme {
                MainScreen()

                val systemUiController = rememberSystemUiController()

                val backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                val useDarkIcons = isSystemInDarkTheme()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = backgroundColor,
                        darkIcons = !useDarkIcons
                    )
                }
//                ProductItem()
            }
        }
    }

    override fun onResume() {
        super.onResume()

//        activitySetting.hideSystemBar(this)
    }
}