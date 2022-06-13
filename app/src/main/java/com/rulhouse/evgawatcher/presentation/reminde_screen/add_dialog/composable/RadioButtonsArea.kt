package com.rulhouse.evgawatcher.presentation.reminde_screen.add_dialog.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.rulhouse.evgawatcher.R

@Preview
@Composable
fun RadioButtonsArea(

) {
    val context = LocalContext.current
    val clickedIndex = remember { mutableStateOf(0) }
    val items = listOf(
        context.getString(R.string.on_time),
        context.getString(R.string.each_time)
    )

    Row(

    ) {
        repeat(items.size) {
            RadioButtonComposable(
                name = items[it],
                clicked = clickedIndex.value == it,
                onClick = {

                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RadioButtonComposable(
    name: String = "固定時間",
    clicked: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = false,
            onClick = { }
        )
        Text(
            text = name
        )
    }
}