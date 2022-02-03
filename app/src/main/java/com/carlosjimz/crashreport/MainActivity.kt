package com.carlosjimz.crashreport

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.RuntimeException

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.setDefaultUncaughtExceptionHandler(
            CustomizedExceptionHandler(
                "/mnt/sdcard/"
            )
        )

        // test the crash
        throw RuntimeException("Testing")
    }

}