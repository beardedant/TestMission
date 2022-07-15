package com.testmission.domain

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun RoomEntityDao(): RoomEntityDao
}