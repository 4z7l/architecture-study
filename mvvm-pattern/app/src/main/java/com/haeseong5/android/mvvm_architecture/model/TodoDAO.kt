package com.haeseong5.android.mvvm_architecture.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.haeseong5.android.mvvm_architecture.model.TodoModel

/**
 * Todo Database CRUD 작업을 위한 인텊페이스
 */
@Dao
interface TodoDAO {
    @Query("SELECT * FROM Todo ORDER BY createdDate ASC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert
    fun insertTodo(todoModel: TodoModel)
}
/**
 * liveData는 액티비티의 생명주기를 인식하며, 데이터의 변화를
 *  Observe 할 수 있다.
 */
