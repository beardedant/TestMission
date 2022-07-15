package com.testmission.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataIn::class], version = 1, exportSchema = true)
abstract class DataInBase : RoomDatabase() {
    abstract fun dataInDao(): DataInDao
}