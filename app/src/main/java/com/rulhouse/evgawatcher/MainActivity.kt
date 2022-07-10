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
import com.rulhouse.evgawatcher.work_manager.factory.WorkManagerFactory
import com.rulhouse.evgawatcher.work_manager.use_cases.WorkManagerUseCases
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManagerUseCases: WorkManagerUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        workManagerUseCases.setPeriodicWork()


        setContent {
            AppTheme {
                MainScreen()
                SystemBarTheme()
            }
        }
    }
}