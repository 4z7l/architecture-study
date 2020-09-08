package com.haeseong5.android.mvvm_architecture.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.haeseong5.android.mvvm_architecture.model.Todo
import com.haeseong5.android.mvvm_architecture.model.TodoDAO
import com.haeseong5.android.mvvm_architecture.model.TodoDatabase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class TodoRepository(application: Application) {
    private var mTodoDatabase: TodoDatabase = TodoDatabase.getInstance(application)
    private var mTodoDAO: TodoDAO = mTodoDatabase.todoDao()
    private var mTodoItems: LiveData<List<Todo>> = mTodoDAO.getTodoList()

    fun getTodoList(): LiveData<List<Todo>> = mTodoItems
    fun insertTodo(todo: Todo) {
        Observable.just(todo)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mTodoDAO.insertTodo(it)
            },{
                it.printStackTrace()
            })
    }
}