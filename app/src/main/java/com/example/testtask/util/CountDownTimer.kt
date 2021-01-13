package com.example.testtask.util

import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Custom timer class
 * @property coroutineContext
 * @property timer - A background job
 */
class CountDownTimer(override val coroutineContext: CoroutineContext) : CoroutineScope {

    var timer: Job = Job()

    /**
     * Method that start new coroutine launch and counts down
     * @param finishTime - delay before executing onFinish
     * @param onFinish - lambda executed at the end of the timer
     */
    fun start(
        finishTime: Long,
        onFinish: () -> Unit
    ) {
        stop()
        timer = launch {
            while (isActive) {
                val leftTime = maxOf(finishTime - System.currentTimeMillis(), 0)
                if (leftTime == 0L) {
                    onFinish()
                    start(5000 + System.currentTimeMillis(), onFinish)
                    cancel()
                }
                delay(1000L)
            }
        }
    }

    /**
     * Method which checks whether the coroutine is active
     */
    private fun stop() {
        if (timer.isActive) {
            timer.cancel()
        }
    }
}