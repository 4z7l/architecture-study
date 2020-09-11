package com.example.architecture_study.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.architecture_study.model.Todo
import com.example.architecture_study.model.TodoDao
import com.example.architecture_study.model.TodoDatabase

class TodoRepository(application: Application) {

    private val todoDatabase = TodoDatabase.getInstance(application)!!
    private val todoDao: TodoDao = todoDatabase.todoDao()
    private val todos: LiveData<List<Todo>> = todoDao.getAll()

    fun getAll(): LiveData<List<Todo>> {
        return todos
    }

    fun insert(todo: Todo) {
        try {
            val thread = Thread(Runnable {
                todoDao.insert(todo) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(todo: Todo) {
        try {
            val thread = Thread(Runnable {
                todoDao.delete(todo)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}