package com.rulhouse.evgawatcher.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.GpuProduct

@Composable
fun ExpandCollapseColumn(
    expandCollapseModel: ExpandCollapseModel,
    onCollapsedStateChanged: (isChecked: Boolean) -> Unit,
    products: List<GpuProduct>
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    onCollapsedStateChanged(!expandCollapseModel.isOpen)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = expandCollapseModel.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
            ToggleButton(expandCollapseModel)
        }
        ProductsList(
            expandCollapseModel = expandCollapseModel,
            products = products
        )
        Divider(thickness = 0.5.dp)
    }
}

@Composable
private fun ProductsList(
    expandCollapseModel: ExpandCollapseModel,
    products: List<GpuProduct>
) {
    AnimatedVisibility(visible = expandCollapseModel.isOpen) {
        LazyColumn(

        ) {
            items(products) { item ->
                GpuProductItem(item = item)
            }
        }
    }
}

@Composable
private fun ToggleButton(
    expandCollapseModel: ExpandCollapseModel
) {
    Icon(
        modifier = Modifier
            .padding(8.dp)
            .size(36.dp),
        imageVector = if (expandCollapseModel.isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        contentDescription = null,
    )
}
