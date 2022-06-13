package com.rulhouse.evgawatcher.crawler.worker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {
        Log.d("doWork", "inputData " + inputData.getString("InputDataKey"))

        val outputData = workDataOf("OutputDataKey" to "OutputDataValue")

        return Result.success(outputData)
    }

    @SuppressLint("RestrictedApi")
    fun workDataOf(vararg pairs: Pair<String, Any?>): Data {
        val dataBuilder = Data.Builder()
        for (pair in pairs) {
            dataBuilder.put(pair.first, pair.second)
        }
        return dataBuilder.build()
    }
}