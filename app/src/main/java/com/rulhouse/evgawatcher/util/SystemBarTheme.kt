package com.rulhouse.evgawatcher.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarTheme(

) {
    val systemUiController = rememberSystemUiController()

    val backgroundColor = MaterialTheme.colorScheme.background
    val useDarkIcons = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
            darkIcons = !useDarkIcons
        )
    }
}