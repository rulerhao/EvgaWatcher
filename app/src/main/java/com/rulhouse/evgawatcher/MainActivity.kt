package com.rulhouse.evgawatcher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.rulhouse.evgawatcher.methods.notification.receiver.NotificationReceiver
import com.rulhouse.evgawatcher.presentation.screen.MainScreen
import com.rulhouse.evgawatcher.ui.theme.AppTheme
import com.rulhouse.evgawatcher.methods.work_manager.use_cases.WorkManagerUseCases
import com.rulhouse.evgawatcher.util.SystemBarTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TestBundle", "value = ${this.intent.getStringExtra(NotificationReceiver().extraName)}")

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        setContent {
            AppTheme {
                MainScreen()
                SystemBarTheme()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d("TestOnNewIntent", "Go")
    }
}