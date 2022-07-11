package com.rulhouse.evgawatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.rulhouse.evgawatcher.presentation.screen.MainScreen
import com.rulhouse.evgawatcher.ui.theme.AppTheme
import com.rulhouse.evgawatcher.methods.work_manager.use_cases.WorkManagerUseCases
import com.rulhouse.evgawatcher.util.SystemBarTheme
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