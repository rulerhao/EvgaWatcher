package com.rulhouse.evgawatcher.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.content.ContextCompat.startActivity
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct


class UriHandler {
    fun openStore(context: Context, uri: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(browserIntent)
    }
}