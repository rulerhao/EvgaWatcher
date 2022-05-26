package com.rulhouse.evgawatcher

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
//    LocalUriHandler.current.openUri("https://tw.evga.com/products/productlist.aspx?type=0")
    val context = LocalContext.current

    AndroidView(factory = {
        WebView(context).apply {
            webViewClient = WebViewClient()

            loadUrl("https://tw.evga.com/products/productlist.aspx?type=0")
        }
    })
}