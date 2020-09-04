package com.project.mvvm.model.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table")
    fun getAll(): LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: ToDo)

    @Query("DELETE from todo_table")
    fun deleteAll()

    @Update
    fun update(todo: ToDo)

    @Delete
    fun delete(todo: ToDo)
}