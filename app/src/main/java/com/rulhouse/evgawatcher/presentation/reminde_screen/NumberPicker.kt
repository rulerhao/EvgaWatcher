package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun NumberPicker(
    range: Iterable<Int> = 0..60
) {
    val number = remember{mutableStateOf(35) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "")
        Icon(imageVector = Icons.Filled.ExpandLess, contentDescription = "")
        Text(text = number.value.toString())
        Icon(imageVector = Icons.Filled.ExpandMore, contentDescription = "")
        Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
    }

}