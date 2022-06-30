package com.rulhouse.evgawatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rulhouse.evgawatcher.methods.ActivitySetting
import com.rulhouse.evgawatcher.presentation.products_screen.item.ProductItem
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

        activitySetting.hindSystemBar(this)

        activitySetting.addSystemUIListener(this)

        setContent {
            AppTheme {
                MainScreen()
//                ProductItem()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        activitySetting.hideSystemBar(this)
    }
}