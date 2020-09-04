package com.project.mvvm.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.project.mvvm.model.datasource.AppDatabase
import com.project.mvvm.model.datasource.ToDo
import com.project.mvvm.model.datasource.ToDoDao

class RoomRepository(application: Application) {

    private val todoDao: ToDoDao by lazy {
        val db = AppDatabase.getInstance(application)!!
        db.todoDao()
    }

    fun getAll(): LiveData<List<ToDo>> {
        return todoDao.getAll()
    }

    fun insert(todo: ToDo){
        todoDao.insert(todo)
    }

    fun update(todo: ToDo){
        todoDao.update(todo)
    }

    fun delete(todo: ToDo){
        todoDao.delete(todo)
    }

    fun deleteAll(){
        todoDao.deleteAll()
    }

}
