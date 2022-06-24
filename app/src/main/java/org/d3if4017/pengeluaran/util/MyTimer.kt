package org.d3if4017.pengeluaran.util

import android.os.Handler
import android.os.Looper
import android.util.Log

class MyTimer {
    private var secondsCount = 0
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            Log.i("Timer", "Detik ke: $secondsCount")
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }
}