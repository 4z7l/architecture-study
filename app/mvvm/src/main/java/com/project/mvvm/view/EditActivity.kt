package com.project.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.mvvm.R
import com.project.mvvm.model.datasource.ToDo
import com.project.mvvm.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        val todos = ToDo(intent.getLongExtra("id",0),"","","",false,false)

        editInputTitle.setText(intent.getStringExtra("title"))
        editInputContents.setText(intent.getStringExtra("contents"))
        edit_button.setOnClickListener {
            todos.postsTitle = editInputTitle.text.toString()
            todos.postsContent = editInputContents.text.toString()
            todos.writtenDate=WriteActivity.getLocalTime()
            toDoViewModel.update(todos)
            finish()
        }



    }
}