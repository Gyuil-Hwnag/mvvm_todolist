package com.example.mvvm_todolist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mvvm_todolist.database.TodoDB2
import com.example.mvvm_todolist.model.Todo2
import com.example.mvvm_todolist.model.TodoDao2

class TodoRepository(application: Application) {
    private val todoDao: TodoDao2
    private val todoList: LiveData<List<Todo2>>

    init {
        var db = TodoDB2.getInstance(application)
        todoDao = db!!.todoDao()
        todoList = db.todoDao().getAll()
    }

    fun insert(todo: Todo2) {
        todoDao.insert(todo)
    }

    fun delete(todo: Todo2){
        todoDao.delete(todo)
    }

    fun getAll(): LiveData<List<Todo2>> {
        return todoDao.getAll()
    }

    fun getDateTodo(query: String?): LiveData<List<Todo2>> {
        return todoDao.getDateTodo(query)
    }

    fun deleteAll() {
        todoDao.deleteAll()
    }
}