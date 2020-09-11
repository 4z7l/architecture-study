package com.example.architecture_study.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_study.R
import com.example.architecture_study.model.Todo
import com.project.mvvm.base.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_todo.*
import java.text.SimpleDateFormat
import java.util.*

class TodoActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        setDate() //현재 날짜 지정

        val adapter = TodoAdapter({ todo -> //onCLick
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_TODO_TITLE, todo.title)
            intent.putExtra(AddActivity.EXTRA_TODO_DATE, todo.date)
            intent.putExtra(AddActivity.EXTRA_TODO_ID, todo.id)
            startActivity(intent)

        }, { todo -> //longClick
            deleteDialog(todo)
        })


        main_recycleview.run {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
        }

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        todoViewModel.getAll().observe(this, Observer<List<Todo>> { todos ->
            adapter.setTodos(todos!!)
        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun setDate(){
        val date = Date()
        val mFormat : SimpleDateFormat = SimpleDateFormat("yyyy년 M월 d일"); // 날짜 포맷
        val time: String = mFormat.format(date)
        tv_today.text = time
    }

    private fun deleteDialog(todo: Todo) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected Todo?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                todoViewModel.delete(todo) //삭제
            }
        builder.show()
    }
}