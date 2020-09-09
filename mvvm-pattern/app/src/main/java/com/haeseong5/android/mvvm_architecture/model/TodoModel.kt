package com.haeseong5.android.mvvm_architecture.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class TodoModel(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "descriptions")
        var descriptions: String,

        @ColumnInfo(name = "createdDate")
        var createdDate: Long

//        @ColumnInfo(name = "progress")
//        var progress: Int

)