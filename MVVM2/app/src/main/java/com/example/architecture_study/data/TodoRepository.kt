package com.example.architecture_study.data

import android.app.Application
import androidx.lifecycle.LiveData

class TodoRepository(application: Application) {
    private var mTodoDatabase: TodoDatabase
    private var mTodoDao: TodoDao
    private var mTodoItems: LiveData<List<Todo>>

    init {
        mTodoDatabase = TodoDatabase.getInstance(application)!!
        mTodoDao = mTodoDatabase.todoDao()
        mTodoItems = mTodoDao.getAll()
    }

    fun getAll(): LiveData<List<Todo>> {
        return mTodoItems
    }

    fun insert(item : Todo) {
        Thread(Runnable {
            mTodoDao.insert(item)
        }).start()
    }

    fun delete(item : Todo) {
        Thread(Runnable {
            mTodoDao.delete(item)
        }).start()
    }
}