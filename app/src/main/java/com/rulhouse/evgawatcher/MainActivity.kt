package com.rulhouse.evgawatcher

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.presentation.MainScreen
import com.rulhouse.evgawatcher.ui.theme.EvgaWatcherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var gpuProductsUseCases: CrawlerUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EvgaWatcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = true) {
                        val nameList = mutableListOf<String>()
                        val gpuItems = gpuProductsUseCases.getGpuItems()
                        gpuItems?.forEach { product ->
                            val pattern = Regex(".* \\d\\d\\d\\d( Ti)?")
                            val found = pattern.find(product.name)
                            val str = found?.value
                            if (str != null) {
                                if (!nameList.contains(str)) {
                                    nameList.add(str)
                                }
                            }
                        }
                    }
                    MainScreen()
                }
            }
        }
    }
}