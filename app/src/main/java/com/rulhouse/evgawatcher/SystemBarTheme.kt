package com.rulhouse.evgawatcher

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarTheme(

) {
    val systemUiController = rememberSystemUiController()

    val backgroundColor = MaterialTheme.colors.background
    val useDarkIcons = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
            darkIcons = !useDarkIcons
        )
    }
}