package com.haeseong5.android.mvvm_architecture.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.haeseong5.android.mvvm_architecture.model.TodoModel
import com.haeseong5.android.mvvm_architecture.model.TodoRepository

/**
 * ViewModel 클래스는 액티비티나 프래그먼트의 UI에 표시될 데이터를 저장하고 관리하는 역할을 한다.
 * ViewModel 클래스에는 새로운 데이터를 요청하거나 변경하는 함수를 구현한다.
 */

/**
 * liveData는 액티비티의 생명주기를 인식하며, 데이터의 변화를 Observe 할 수 있다.
 */
class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val mTodoRepository: TodoRepository = TodoRepository(application)
    private var mTodoModelItems: LiveData<List<TodoModel>> = mTodoRepository.getTodoList()

    fun insertTodo(todoModelModel: TodoModel) {
        mTodoRepository.insertTodo(todoModelModel)
    }

    fun getTodoList(): LiveData<List<TodoModel>> {
        return mTodoModelItems
    }
}

