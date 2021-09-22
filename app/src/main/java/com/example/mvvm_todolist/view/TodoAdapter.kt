package com.example.mvvm_todolist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_todolist.R
import com.example.mvvm_todolist.model.Todo

class TodoAdapter(val context: Context, val todolist: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.Holder>() {

    override fun getItemCount(): Int {
        return todolist.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val index = itemView?.findViewById<TextView>(R.id.index)
        val todos = itemView?.findViewById<TextView>(R.id.todo)
        val delete_btn = itemView?.findViewById<ImageView>(R.id.delete_btn)

        fun bind(todo: Todo) {
            index?.text = todo.id.toString()
            todos?.text = todo.todo.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(todolist[position])
    }
}