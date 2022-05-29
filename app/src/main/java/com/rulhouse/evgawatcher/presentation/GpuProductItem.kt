package com.rulhouse.evgawatcher.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.GpuProduct
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GpuProductItem(
    item: GpuProduct
) {
    GlideImage( // CoilImage, FrescoImage
        imageModel = item.imgUrl,
//        modifier = modifier,
        // shows an indicator while loading an image.
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        // shows an error text if fail to load an image.
        failure = {
            Text(
                text = "image request failed."
            )
        }
    )
    Text(text = item.name)
}