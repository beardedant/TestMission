package com.testmission.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "datain")
data class DataIn(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val square: String?,
    val sortingArray: String?,
    val containerArray: String?,
    val timeStamp: Long
) : Parcelable