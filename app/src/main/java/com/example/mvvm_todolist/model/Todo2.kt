package com.example.mvvm_todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo2 (
    var date: String,
    var content: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

//@Entity(tableName = "todo2")
//data class Todo2(
//    @ColumnInfo(name="date")
//    val date: String,
//    var content: String
//) {
//    @PrimaryKey(autoGenerate = true) var id: Int = 0
//}