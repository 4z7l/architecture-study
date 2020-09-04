package com.project.mvvm.model.datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDo (@PrimaryKey(autoGenerate = true) var id : Long?,
                 @ColumnInfo(name = "title")var postsTitle: String?,
                 @ColumnInfo(name = "written_date")var writtenDate : String?,
                 @ColumnInfo(name = "posts_content")var postsContent : String?,
                 @ColumnInfo(name = "checked")var checked : Boolean,
                 @ColumnInfo(name = "delete_check")var deleteChecked : Boolean)  {
    constructor(): this(null,"", "","",false,false)
}