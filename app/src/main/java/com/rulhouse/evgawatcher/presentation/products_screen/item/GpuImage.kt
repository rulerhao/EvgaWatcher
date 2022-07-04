package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.methods.ScreenMethods
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun GpuImage(
    imgUrl: String?,
    size: IntSize
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        GlideImage(
            modifier = Modifier
                .padding(24.dp)
                .background(MaterialTheme.colorScheme.tertiary)
                .height(ScreenMethods.convertPixelToDp(size.height.toFloat(), LocalContext.current).dp),
            imageModel = imgUrl,
            contentScale = ContentScale.Fit,
            loading = {
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Text(
                    text = "image request failed."
                )
            }
        )
    }
}