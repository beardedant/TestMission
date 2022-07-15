package com.testmission.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomEntityDao {
    //select, insert
    @Query("Select * from roomentity")
    fun getAll() : List<RoomEntity>

    @Insert
    fun insert(roomEntity: RoomEntity)
}