package com.example.mvvm_todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo2 (
    var content: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}