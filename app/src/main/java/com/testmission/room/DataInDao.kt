package com.testmission.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataInDao {
    //select, insert
    @Query("Select * from datain")
    fun getAll() : List<DataIn>

    @Insert
    fun insert(dataIn: DataIn)
}