package com.bhagyawant.weatherapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    init {
        job = Job()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }




}