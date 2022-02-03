package com.carlosjimz.crashreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.setDefaultUncaughtExceptionHandler(
            CustomizedExceptionHandler(
                Thread.getDefaultUncaughtExceptionHandler(),
                "/mnt/sdcard/"
            )
        )

        // test the crash
        val nullString = null;
        println(nullString.toString())
    }

}