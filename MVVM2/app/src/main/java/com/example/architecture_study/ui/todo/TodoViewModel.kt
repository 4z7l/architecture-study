package com.example.architecture_study.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecture_study.data.Todo
import com.example.architecture_study.data.TodoRepository

/** ViewModel
 * - View에 필요한 모든 데이터를 가짐
 * - Model은 알고, View는 모른다
 * - View에게 관찰당하므로 UI update 명령을 내리지 않아도 됨
 * - View는 ViewModel 관찰하다가 자동으로 UI update
 *
 *
 * - Room을 만들 때 context가 필요하나 view의 context를 쓰면 메모리 누수 발생 --> application context 사용
 */
class TodoViewModel(application: Application):AndroidViewModel(application) {
    private val mTodoRepository : TodoRepository
    private val mTodoItems : LiveData<List<Todo>>

    init{
        mTodoRepository = TodoRepository(application)
        mTodoItems = mTodoRepository.getAll()
    }

    fun getAll() : LiveData<List<Todo>> {
        return mTodoItems
    }

    fun insert(item : Todo){
        mTodoRepository.insert(item)
    }

    fun delete(item : Todo){
        mTodoRepository.delete(item)
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoViewModel(application) as T
        }
    }
}