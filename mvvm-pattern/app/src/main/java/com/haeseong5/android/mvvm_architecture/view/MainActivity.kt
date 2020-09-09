package com.haeseong5.android.mvvm_architecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haeseong5.android.mvvm_architecture.R
import com.haeseong5.android.mvvm_architecture.model.TodoModel
import com.haeseong5.android.mvvm_architecture.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var mTodoAdapter: TodoAdapter
    private var todoModelItems: List<TodoModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView(){
        main_floating_action_button.setOnClickListener(this)
        mTodoAdapter = TodoAdapter(this@MainActivity)
        main_recyclerView.adapter = mTodoAdapter
        main_recyclerView.layoutManager =  LinearLayoutManager(this)
        main_recyclerView.setHasFixedSize(true)
    }

    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)
        mTodoViewModel.getTodoList().observe(this, Observer {
            mTodoAdapter.setTodoItems(it as ArrayList<TodoModel>)
        })
    }

    private fun showDialog(){
        val dialog =  TodoDialog(this@MainActivity)
        dialog.setOnClickedListener { title, description ->
            toast(title + description)
            val createdDate = Date().time
            val todoModel = TodoModel(null, title, description, createdDate)
            mTodoViewModel.insertTodo(todoModel)
        }
        dialog.start()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            main_floating_action_button.id -> {
                showDialog()
            }
        }
    }
}