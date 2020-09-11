package org.techtown.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import org.techtown.mvvm.dao.MemoDao
import org.techtown.mvvm.database.DB
import org.techtown.mvvm.entity.Memo

class MemoRepository {
    private val db: DB
    private val memoDao: MemoDao
    private val memoList: LiveData<List<Memo>>

    constructor(context: Context){
        db = DB.getInstance(context)!!
        memoDao = db.memoDao()
        memoList = memoDao.getAll()
    }

    fun insert(memo: Memo) = memoDao.insert(memo)

    fun getAll(): LiveData<List<Memo>> = memoList
}