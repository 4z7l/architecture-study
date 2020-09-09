package com.example.architecture_study.ui.todo

import android.app.Application
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_study.R
import com.example.architecture_study.data.Todo
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import java.util.*

class TodoActivity : AppCompatActivity() {

    private lateinit var mTodoViewModel : TodoViewModel
    private lateinit var mTodoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initView()
        initViewModel()
    }

    fun initView() {
        // recyclerview
        mTodoAdapter = TodoAdapter()
        recyclerview_todo.adapter = mTodoAdapter
        recyclerview_todo.layoutManager = LinearLayoutManager(this)
        recyclerview_todo.setHasFixedSize(true)

        //dialog
        fab_add.setOnClickListener{
            openAddTodoDialog()
        }
    }

    fun initViewModel() {
        //mTodoViewModel = ViewModelProvider(this,TodoViewModel.Factory(application)).get(TodoViewModel::class.java)
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)
        mTodoViewModel.getAll().observe(this, Observer<List<Todo>> { items ->
            mTodoAdapter.setTodoItems(items)
        })
    }

    fun openAddTodoDialog(){
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo,null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Todo 추가")
            .setView(dialogView)
            .setPositiveButton("확인", {dialogInterface, i ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val date = Date().time

                val todoitem = Todo(null,title,description,date)
                mTodoViewModel.insert(todoitem)
            })
            .setNegativeButton("취소",null)
            .create()
        dialog.show()
    }

}