package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun RepeatedRemindItem(
    reminderState: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit) = {
    },
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Alarm,
            contentDescription = ""
        )
        Text(
            text = "",

        )
        Text(
            text = ""
        )
        Switch(
            checked = reminderState,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
    }
}