package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.RemindersScreenEvent
import com.rulhouse.evgawatcher.methods.work_manager.use_cases.WorkManagerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.ExecutionException
import javax.inject.Inject


@HiltViewModel
class RemindersScreenViewModel @Inject constructor(
    private val workManagerUseCases: WorkManagerUseCases
): ViewModel() {

    private val workManagerStateLiveData: LiveData<List<WorkInfo>> = workManagerUseCases.getPeriodWorkStateLiveData()

    private val observer = Observer<List<WorkInfo>> {
        _workScheduled.value = isWorkScheduled(it)
    }

    private val _workScheduled: MutableState<Boolean> =
        mutableStateOf(false)
    val workScheduled: State<Boolean> = _workScheduled

    init {
        workManagerStateLiveData.observeForever(observer)
    }

    fun onEvent(event: RemindersScreenEvent) {
        when(event) {
            RemindersScreenEvent.OnWorkManagerSwitchClick -> {
                if (workScheduled.value) workManagerUseCases.cancelPeriodicWork()
                else workManagerUseCases.setPeriodicWork()
            }
        }
    }

    private fun isWorkScheduled(workInfoList: List<WorkInfo>): Boolean {
        return try {
            var running = false
            for (workInfo in workInfoList) {
                val state = workInfo.state
                running = state == WorkInfo.State.RUNNING || state == WorkInfo.State.ENQUEUED
            }
            running
        } catch (e: ExecutionException) {
            e.printStackTrace()
            false
        } catch (e: InterruptedException) {
            e.printStackTrace()
            false
        }
    }
}