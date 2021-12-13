package com.example.mvvm_todolist.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.example.mvvm_todolist.database.TodoDB
import com.example.mvvm_todolist.databinding.ActivityMainBinding
import com.example.mvvm_todolist.viewmodel.CallAnotherActivityModel
import com.example.mvvm_todolist.viewmodel.CallAnotherActivityNavigator
import com.example.mvvm_todolist.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), CallAnotherActivityNavigator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databinding: ActivityMainBinding
    private val model = CallAnotherActivityModel(this)
    private val list_model: TodoViewModel by viewModels()

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
        binding.searchBtn.setOnClickListener {
            val days: List<Calendar> = calendarView.selectedDates
            var result = ""
            for (i in days.indices) {
                val calendar: Calendar = days[i]
                val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val month: Int = calendar.get(Calendar.MONTH)
                val year: Int = calendar.get(Calendar.YEAR)
                val week: String = SimpleDateFormat("EE").format(calendar.getTime())
                val day_full = year.toString() + "년" + (month + 1) + "월" + day + "일" + week
                result += """$day_full""".trimIndent()
            }
//            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
            binding.editText.text = result
        }

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
        intent.putExtra("date",binding.editText.text.toString())
        startActivity(intent)
    }
}