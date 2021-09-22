package com.example.mvvm_todolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_todolist.databinding.TodoItemBinding
import com.example.mvvm_todolist.model.Todo
import com.example.mvvm_todolist.model.Todo2
import com.example.mvvm_todolist.viewmodel.OnItemClick

class TodoAdapter2(listener: OnItemClick) : RecyclerView.Adapter<TodoAdapter2.TodoViewHolder>() {

    private val mCallback = listener
    private val items = ArrayList<Todo2>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(todo: List<Todo2>) {
        items.clear()
        items.addAll(todo)
    }

    inner class TodoViewHolder(private val binding: TodoItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: Todo2){
            binding.index.text = item.id.toString()
            binding.todo.text = item.content
            binding.deleteBtn.setOnClickListener {
                mCallback.deleteTodo(item)
            }
        }
    }
}