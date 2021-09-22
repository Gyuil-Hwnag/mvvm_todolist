package com.example.mvvm_todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_todolist.model.Todo2
import com.example.mvvm_todolist.model.TodoDao2

@Database(entities = [Todo2::class], version = 1, exportSchema = false)
abstract class TodoDB2 : RoomDatabase() {
    abstract fun todoDao(): TodoDao2

    companion object {
        private var instance: TodoDB2? = null

        @Synchronized
        fun getInstance(context: Context): TodoDB2? {
            if (instance == null) {
                synchronized(TodoDB2::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDB2::class.java,
                        "todo2.db"
                    ).build()
                }
            }
            return instance
        }
    }
}