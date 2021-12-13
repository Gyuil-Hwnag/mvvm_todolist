package com.example.mvvm_todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_todolist.model.Todo
import com.example.mvvm_todolist.model.Todo2
import com.example.mvvm_todolist.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoRepository(application)
    private val items = repository.getAll()

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoViewModel(application) as T
        }
    }

    fun insert(todo: Todo2) {
        repository.insert(todo)
    }

    fun delete(todo: Todo2){
        repository.delete(todo)
    }

    fun getDateTodo(searchTxt: String): LiveData<List<Todo2>> {
        var items = repository.getDateTodo(searchTxt)
        return items
    }

//    fun getDateTodo(searchTxt: String){
//        repository.getDateTodo(searchTxt)
////        Log.d("DiscoverViewModel", "Search $searchTxt")
//    }

    fun getAll2(): LiveData<List<Todo2>> {
        return items
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}