package org.techtown.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.techtown.mvvm.dao.MemoDao
import org.techtown.mvvm.entity.Memo

@Database(entities = [Memo::class], version = 1)
abstract class DB : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object{
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB?{
            if(INSTANCE == null){
                synchronized(DB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DB::class.java, "DB.db")
                        .addCallback(CALLBACK)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }

        private val CALLBACK = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // TODO
            }
        }

    }
}