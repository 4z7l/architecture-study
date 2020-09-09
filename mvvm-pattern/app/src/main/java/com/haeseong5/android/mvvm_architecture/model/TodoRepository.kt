package com.haeseong5.android.mvvm_architecture.model

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.haeseong5.android.githubwithrx.src.kotlin.rx.AutoActivatedDisposable
import com.haeseong5.android.githubwithrx.src.kotlin.rx.AutoClearedDisposable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Repository 패턴은 데이터베이스 혹은 네트워크를 통하여 데이터를 얻는 기능을 분리한다.
 * ViewModel에서는 이 Repository를 통해 데이터를 얻는다.
 */
class TodoRepository(application: Application) {
    private var mTodoDatabase: TodoDatabase = TodoDatabase.getInstance(application)
    private var mTodoDAO: TodoDAO = mTodoDatabase.todoDao()
    private var mTodoModelItems: LiveData<List<TodoModel>> = mTodoDAO.getTodoList()


    fun getTodoList(): LiveData<List<TodoModel>> = mTodoModelItems
    fun insertTodo(todoModel: TodoModel) {
        mTodoDAO.insertTodo(todoModel) //전달받은 todoModel 데이터를 DAO인터페이스를 통해 실제 DB에 삽입.

//        Single.just(todoModel) //이벤트 발생 시 data 객체 전달.
//
//            .subscribeOn(Schedulers.io()) //io 스레드에서 처리
//            .subscribe({
//                mTodoDAO.insertTodo(it) //전달받은 todoModel 데이터를 DAO인터페이스를 통해 실제 DB에 삽입.
//            },{
//                it.printStackTrace()
//            })
    }
}