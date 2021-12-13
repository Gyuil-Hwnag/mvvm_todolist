package com.example.mvvm_todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_todolist.databinding.ActivityDetailBinding
import com.example.mvvm_todolist.model.Todo2
import com.example.mvvm_todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// room DB 1 (MVVM 이 아닌 MVC 모델에서 사용시)
//class DetailActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityDetailBinding
//    private var todoDB : TodoDB? = null
//    private var todoList = listOf<Todo>()
//    lateinit var mAdapter : TodoAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // room DB 1 (MVVM 이 아닌 MVC 모델에서 사용시)
//        todoDB = TodoDB.getInstance(this)
//        mAdapter = TodoAdapter(this, todoList)
//
//        val r = Runnable {
//            // 데이터에 읽고 쓸때는 쓰레드 사용
//            try {
//                todoList = todoDB?.todoDao()?.getAll()!!
//                mAdapter = TodoAdapter(this, todoList)
//                mAdapter.notifyDataSetChanged()
//
//
//                binding.recyclerItem.adapter = mAdapter
//                binding.recyclerItem.layoutManager = LinearLayoutManager(this)
//                binding.recyclerItem.setHasFixedSize(true)
//            } catch (e: Exception) {
//                Log.d("tag", "Error - $e")
//            }
//        }
//
//        val thread = Thread(r)
//        thread.start()
//
//        /* 새로운 todo 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
//        val addRunnable = Runnable {
//            val newTodo = Todo()
//            newTodo.date = binding.dateTxt.text.toString()
//            newTodo.todo = binding.editText.text.toString()
//            todoDB?.todoDao()?.insert(newTodo)
//        }
//
//        binding.addBtn.setOnClickListener {
//            val addThread = Thread(addRunnable)
//            addThread.start()
//            val thread = Thread(r)
//            thread.start()
//        }
//    }
//}


// room DB 2 (MVVM 모델에서 사용시)
class DetailActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityDetailBinding
    /*
    초기화 코드에 아무것도 존재하지 않으면 첫 줄의 viewModels()로 간단하게 처리가 가능하고, 초기화 코드가 들어가야 한다면 2번째 방법을 선택해야 한다.
     */
    private val model: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        var date = intent.getStringExtra("date")
        binding.dateTxt.text = date.toString()

        model.getDateTodo(date.toString()).observe(this, Observer{
            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    // Do something
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    Log.d("data_get_success", it.size.toString())
                }
            }
        })
        binding.editText.text.clear()

        binding.addBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                model.insert(Todo2(binding.dateTxt.text.toString() ,binding.editText.text.toString()))
            }
            binding.editText.text.clear()
        }

        val delete = Runnable {
            // 목록 전부 비우기 스레드
            try {
                model.deleteAll()
                model.getAll2().observe(this, Observer{
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                })
                binding.editText.text.clear()
            }
            catch (e: Exception) {
                Log.d("delete_fialed", "Error - $e")
            }
        }

        binding.clearBtn.setOnClickListener {
            val thread = Thread(delete)
            thread.start()
            binding.editText.text.clear()
        }
    }

    private fun initRecyclerView(){
        binding.recyclerItem.layoutManager = LinearLayoutManager(this)
        adapter = TodoAdapter2(this)
        binding.recyclerItem.adapter = adapter
    }

    override fun deleteTodo(todo: Todo2) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(todo)
        }
    }
}