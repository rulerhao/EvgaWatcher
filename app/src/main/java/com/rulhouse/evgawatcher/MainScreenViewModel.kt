package com.rulhouse.evgawatcher

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    crawlerUseCases: CrawlerUseCases
) : ViewModel() {
    init {
        viewModelScope.launch {
            Log.d("TestText", "Test = ${crawlerUseCases.getGpuItems()}")
        }
    }
}