package com.example.architecture_study.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.architecture_study.R
import com.example.architecture_study.model.Todo
import com.project.mvvm.base.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_add.*


class AddActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)  //viewmodel 재사용

        val timePicker = findViewById<TimePicker>(R.id.tp_time)
        timePicker.setIs24HourView(true)
        timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            val AP = getAmPm(hour)
            val newHour = get24Hour(hour)
            add_et_date.text = "$AP $newHour:$minute"
        }

        // intent로 넘어올 경우, intent null check & get extras
        if (intent != null && intent.hasExtra(EXTRA_TODO_TITLE) && intent.hasExtra(EXTRA_TODO_ID)) {
            add_et_title.setText(intent.getStringExtra(EXTRA_TODO_TITLE))
            add_et_date.text = intent.getStringExtra(EXTRA_TODO_DATE)
            id = intent.getLongExtra(EXTRA_TODO_ID, 1)
        }

        add_button.setOnClickListener {
            val title = add_et_title.text.toString()
            val date = add_et_date.text.toString()
            val text = "sub title"

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please enter todo and date.", Toast.LENGTH_SHORT).show()

            } else {
                val todo = Todo(id, title, text, date)
                todoViewModel.insert(todo)
                finish()
            }
        }
    }

    private fun getAmPm(hour: Int): String? {
        return if (hour >= 12) "오후" else "오전"
    }

    private fun get24Hour(hour: Int): Int? {
        return if (hour >= 13) (hour-12) else hour
    }

    companion object {
        const val EXTRA_TODO_TITLE = "EXTRA_TODO_TITLE"
        const val EXTRA_TODO_DATE = "EXTRA_TODO_NUMBER"
        const val EXTRA_TODO_ID = "EXTRA_TODO_ID"
    }
}