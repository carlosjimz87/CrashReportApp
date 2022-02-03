package com.carlosjimz.crashreport

import android.os.Environment
import android.util.Log
import java.io.*
import java.lang.Thread.UncaughtExceptionHandler
import java.text.SimpleDateFormat
import java.util.*


class CustomizedExceptionHandler(
    private val localPath: String?
) : UncaughtExceptionHandler {

    private val defaultUEH: UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(t: Thread, e: Throwable) {
        println("Uncaught exception executed")
        //Write a printable representation of this Throwable
        //The StringWriter gives the lock used to synchronize access to this writer.
        val stringBuffSync: Writer = StringWriter()
        val printWriter = PrintWriter(stringBuffSync)
        e.printStackTrace(printWriter)
        val stacktrace = stringBuffSync.toString()
        printWriter.close()
        if (localPath != null) {
            writeToFile(stacktrace)
        }

        //Used only to prevent from any code getting executed.
        // Not needed in this example
        defaultUEH.uncaughtException(t, e)
    }

    private fun writeToFile(currentStacktrace: String) {
        println("Writing file")
        try {

            //Gets the Android external storage directory & Create new folder Crash_Reports
            val dir = File(
                Environment.getExternalStorageDirectory(),
                "Crash_Reports"
            )
            if (!dir.exists()) {
                dir.mkdirs()
            }

            // Write the file into the folder
            val reportFile = File(dir, getFilenameDate())
            val fileWriter = FileWriter(reportFile)
            fileWriter.append(currentStacktrace)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            Log.e("ExceptionHandler", e.message!!)
        }
    }

    private fun getFilenameDate(): String {
        println("Creating Date")
        val date = Date().toString()
        return "$date.STACKTRACE"
    }

}