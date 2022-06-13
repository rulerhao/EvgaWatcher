package com.rulhouse.evgawatcher.presentation.reminde_screen.add_dialog.composable.on_time

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import java.util.*

@Composable
fun OnTimePicker(

) {
    val timeSetListener =
        TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            val calendar = Calendar.getInstance()
            calendar.time = Date(nowTime.value)
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            nowTime.value = calendar.timeInMillis
            timeStringState.value = TimeMethods.getTimeStringWithoutSec(
                context = context,
                timeStamp = nowTime.value
            )
            mOnContentChanged()
        }
}

fun invokeTimePicker(
    context: Context,
    timeSetListener: TimePickerDialog.OnTimeSetListener,
    nowTime: Long
) {
    val nowCalendar = Calendar.getInstance()
    nowCalendar.time = Date(nowTime)

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