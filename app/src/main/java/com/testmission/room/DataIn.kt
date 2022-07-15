package com.testmission.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "datain")
data class DataIn(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val value: String,
    val timeStamp: Long
)