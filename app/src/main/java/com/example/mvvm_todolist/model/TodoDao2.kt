package com.example.mvvm_todolist.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao2 {
    @Query("SELECT * FROM todo2")
    fun getAll(): LiveData<List<Todo2>>

    @Insert
    fun insert(todo: Todo2)

    @Update
    fun update(todo: Todo2)

    @Delete
    fun delete(todo: Todo2)

    @Query("DELETE FROM todo2")
    fun deleteAll()
}