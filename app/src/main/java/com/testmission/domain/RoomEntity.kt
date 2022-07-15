package com.testmission.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey
    val id: Int,
    val type: String,
    val value: String,
    val timeStamp: Long
)