package com.example.mvvm_todolist.view

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.example.mvvm_todolist.database.TodoDB
import com.example.mvvm_todolist.databinding.ActivityMainBinding
import com.example.mvvm_todolist.viewmodel.CallAnotherActivityModel
import com.example.mvvm_todolist.viewmodel.CallAnotherActivityNavigator


class MainActivity : AppCompatActivity(), CallAnotherActivityNavigator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databinding: ActivityMainBinding
    private val model = CallAnotherActivityModel(this)

    private var todoDB : TodoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setModel(model)
//        databinding = DataBindingUtil.setContentView(this, R.layout.ac)
//        binding.setModel(model)

        var calendarView = binding.calendarView

        calendarView.isShowDaysOfWeekTitle = false
        calendarView.setSelectionType(SelectionType.SINGLE)

        // room DB
        todoDB = TodoDB.getInstance(this)

        val r = Runnable {
            // 데이터에 읽고 쓸때는 쓰레드 사용
        }

        val thread = Thread(r)
        thread.start()
    }

    override fun callActivity() {
        var intent = Intent(applicationContext, DetailActivity::class.java)
        startActivity(intent)
    }
}