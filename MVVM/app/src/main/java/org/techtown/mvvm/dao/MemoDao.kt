package org.techtown.mvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.techtown.mvvm.entity.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): LiveData<List<Memo>>

    @Insert(onConflict = REPLACE)
    fun insert(memo: Memo)

    @Query("DELETE FROM memo")
    fun deleteAll()
}