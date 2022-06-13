package com.rulhouse.evgawatcher.presentation.reminde_screen.add_dialog.composable.on_time

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.*

@Preview
@Composable
fun OnTimePicker(

) {
    val context = LocalContext.current

    val hourState = remember{ mutableStateOf(0)}
    val minuteState = remember{ mutableStateOf(0)}

    val timeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            hourState.value = hour
            minuteState.value = minute
        }

    Row(
        modifier = Modifier
            .clickable {
                invokeTimePicker(
                    context = context,
                    timeSetListener = timeSetListener
                )
            }
    ) {
        Text(
            text = hourState.value.toString()
        )
        Text(
            text = minuteState.value.toString()
        )
    }
}

fun invokeTimePicker(
    context: Context,
    timeSetListener: TimePickerDialog.OnTimeSetListener,
) {
    val nowCalendar = Calendar.getInstance()
    nowCalendar.time = Date(System.currentTimeMillis())

    val hour: Int = nowCalendar.get(Calendar.HOUR_OF_DAY)
    val minute: Int = nowCalendar.get(Calendar.MINUTE)
    TimePickerDialog(
        context,
        timeSetListener,
        hour,
        minute,
        true
    ).apply {
        show()
    }
}