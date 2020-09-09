package com.example.architecture_study.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll() : LiveData<List<Todo>>

    @Insert
    fun insert(item: Todo)

    @Delete
    fun delete(item: Todo)
}