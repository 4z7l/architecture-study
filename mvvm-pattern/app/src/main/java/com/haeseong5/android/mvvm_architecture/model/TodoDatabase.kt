package com.haeseong5.android.mvvm_architecture.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TodoModel::class), version = 2)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDAO

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase = instance
            ?:
                synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        )
                            .also { instance = it }
                }

        private fun buildDatabase(context: Context)
            = Room.databaseBuilder(context.applicationContext,
            TodoDatabase::class.java, "Todo.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}