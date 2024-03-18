package com.example.enrollify

import android.app.Application
import com.example.enrollify.data.EnrollifyAppContainer
import com.example.enrollify.data.EnrollifyAppDataContainer

class EnrollifyApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: EnrollifyAppContainer

    override fun onCreate() {
        super.onCreate()
        container = EnrollifyAppDataContainer(this)
    }
}