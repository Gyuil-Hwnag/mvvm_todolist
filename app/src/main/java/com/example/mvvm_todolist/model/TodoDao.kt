package com.example.mvvm_todolist.model

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

//    @Delete
//    fun delete(todo: Todo)

    @Query("DELETE from todo")
    fun deleteAll()
}