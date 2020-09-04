package com.project.mvvm.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.mvvm.R
import com.project.mvvm.model.datasource.ToDo
import com.project.mvvm.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_write.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteActivity : AppCompatActivity() {
    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        add_button.setOnClickListener{
            val todos = ToDo()
            todos.postsTitle= inputTitle.text.toString()
            todos.postsContent= inputContents.text.toString()
            todos.writtenDate = getLocalTime()
            toDoViewModel.insert(todos)
            finish()
        }
    }
    companion object {
        fun getLocalTime(): String? {
            lateinit var formatted: String
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now().plusHours(9)
                val formatter = DateTimeFormatter.ofPattern("HH : mm")
                formatted = current.format(formatter)
            }
            return formatted

        }
    }
}