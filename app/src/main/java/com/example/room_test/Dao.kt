package com.example.room_test

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface Dao {

    @Insert
    suspend fun addTask(task: Task)

    @Query("select * from task order by id ASC")
    fun getTasks():List<Task>
}