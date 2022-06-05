package com.rulhouse.evgawatcher.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GpuProductItem(
    item: GpuProduct,
    onClick: (GpuProduct) -> Unit
) {
    val isFavorite = false
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(vertical = 8.dp)
            .clickable {
                onClick(item)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            GlideImage( // CoilImage, FrescoImage
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp),
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
            Text(
                text = item.name,
                color = MaterialTheme.colors.primary
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                ) {
                    if (item.price != 0)
                        Text(
                            modifier = Modifier
                                .align(Alignment.Bottom),
                            text = item.price.toString(),
                            color = if (item.canBeBought!!) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.h5
                        )
                }
            }
        }
    }
}