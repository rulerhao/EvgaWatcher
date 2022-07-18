package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.R

@Preview
@Composable
fun RemindItem(
    modifier: Modifier = Modifier,
    reminderState: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit) = {
    },
) {

    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                imageVector = Icons.Filled.Alarm,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.reminder),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Switch(
            modifier = Modifier
                .fillMaxHeight(),
            checked = reminderState,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
    }
}