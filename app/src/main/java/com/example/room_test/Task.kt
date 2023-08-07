package com.example.room_test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task")
data class Task (val name: String) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}