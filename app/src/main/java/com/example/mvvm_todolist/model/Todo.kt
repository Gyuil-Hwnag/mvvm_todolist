package com.example.mvvm_todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class Todo(@PrimaryKey var id: Long?,
           @ColumnInfo(name = "date") var date: String?,
           @ColumnInfo(name = "todo") var todo: String
){
    constructor(): this(null,"", "")
}

/* 기본키가 별의미가 없다면
@Entity
class Todo(@PrimaryKey(autoGenerate = true) var id: Long?,
          @ColumnInfo ...
)
*/
