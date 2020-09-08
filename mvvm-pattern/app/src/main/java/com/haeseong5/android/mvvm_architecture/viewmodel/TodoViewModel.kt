package com.haeseong5.android.mvvm_architecture.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.haeseong5.android.mvvm_architecture.model.Todo
import com.haeseong5.android.mvvm_architecture.model.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val mTodoRepository: TodoRepository =
        TodoRepository(application)
    private var mTodoItems: LiveData<List<Todo>> = mTodoRepository.getTodoList()

    fun insertTodo(todoModel: Todo) {
        mTodoRepository.insertTodo(todoModel)
    }

    fun getTodoList(): LiveData<List<Todo>> {
        return mTodoItems
    }
}

