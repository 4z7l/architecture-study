package org.techtown.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.techtown.mvvm.entity.Memo
import org.techtown.mvvm.repository.MemoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val _repository = MemoRepository(context)

    private val _memoList = _repository.getAll()

    /* 외부 클래스 사용 위함 */
    val repository = _repository
    val memoList = _memoList

    /* memo 추가 메서드 */
    fun insertMemo(memo: Memo){
            GlobalScope.launch {
                repository.insert(memo)
            }
    }
}