package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Image(
    imgUrl: String?
) {
    GlideImage(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxHeight(0.5f),
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