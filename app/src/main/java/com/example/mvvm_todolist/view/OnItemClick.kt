package com.example.mvvm_todolist.viewmodel

import com.example.mvvm_todolist.model.Todo2

interface OnItemClick {
    fun deleteTodo(todo: Todo2)
//    fun checkTodo(todo: Todo2)
}