package com.testmission

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.testmission.room.DataInBase


class App : Application() {

    companion object {
        lateinit var instance: App
            private set
        lateinit var database: DataInBase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, DataInBase::class.java, "dataInBase")
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App