package com.example.mvvm_todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_todolist.model.Todo
import com.example.mvvm_todolist.model.TodoDao

@Database(entities = [Todo::class], version = 1)
abstract class TodoDB: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context): TodoDB? {
            if (INSTANCE == null) {
                synchronized(TodoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TodoDB::class.java, "todo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}