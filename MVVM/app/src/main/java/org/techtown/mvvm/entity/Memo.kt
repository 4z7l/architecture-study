package org.techtown.mvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class Memo(@PrimaryKey(autoGenerate = true) var id: Int?,
                @ColumnInfo(name = "date") var date: String,
                @ColumnInfo(name = "contents") var contents: String){
    constructor() : this(null,"","")
}