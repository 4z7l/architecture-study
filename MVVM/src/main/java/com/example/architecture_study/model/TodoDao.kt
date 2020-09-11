package com.example.architecture_study.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.architecture_study.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getAll(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Todo)

    @Delete
    fun delete(contact: Todo)

}