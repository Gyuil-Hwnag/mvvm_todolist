package com.example.mvvm_todolist.model

import androidx.room.Entity
import androidx.room.Fts4
import com.example.mvvm_todolist.database.TodoDB2

@Entity(tableName = "todoFind")
@Fts4(contentEntity = TodoDB2::class)
class TodoFind(
    val date: String
)