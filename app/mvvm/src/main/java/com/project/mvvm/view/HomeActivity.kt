package com.project.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mvvm.R
import com.project.mvvm.model.datasource.ToDo
import com.project.mvvm.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_home.*
import java.nio.file.Files.delete

class HomeActivity : AppCompatActivity(), ToDoAdapter.TodoItemClickListener, ToDoAdapter.selection {

    private lateinit var toDoViewModel: ToDoViewModel
    private lateinit var todoAdapter: ToDoAdapter
    var delete = false
    var selectall = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        todoAdapter = ToDoAdapter(this, this)
        todoAdapter.setHasStableIds(true)
        recyclerView.adapter=todoAdapter

        toDoViewModel.getAll().observe(this, Observer {
            todoAdapter.apply {
                list = it
                notifyDataSetChanged()
            }
        })

        writingButton.setOnClickListener {
            val intent = Intent(applicationContext, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.delete_mode -> {
                if(todoAdapter.list.isEmpty()){
                    Toast.makeText(applicationContext,"지울 목록 없습니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    delete =!delete
                    todoAdapter.selectAll=1

                    todoAdapter.deleteMode = true
                    todoAdapter.notifyDataSetChanged()
                    invalidateOptionsMenu()
                }
            }
            R.id.select_all->{
                selectAll()
            }
            R.id.cancel_action->{
                cancelDelete()
            }
            R.id.delete->{
                delete()
            }
            R.id.cancel_select->{
                cancelSelect()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setDeleteMode(){
        delete=true
        invalidateOptionsMenu()
    }

    override fun onDeleteClicked(todoItem: ToDo) {
        toDoViewModel.delete(todoItem)
    }

    override fun onLongClicked() {
        setDeleteMode()
    }

    override fun onCheckBoxClicked(todoItem: ToDo) {
        toDoViewModel.toggleCheckedState(todoItem)
    }

    override fun onDeleteBoxClicked(todoItem: ToDo) {
        todoAdapter.selectAll=0
        toDoViewModel.toggleDeleteCheckedState(todoItem)
    }

    override fun checkDeleteTrue(todoItem: ToDo) {
        toDoViewModel.toggleDeleteCheckedStateTrue(todoItem)
    }

    override fun checkDeleteFalse(todoItem: ToDo) {
        toDoViewModel.toggleDeleteCheckedStateFalse(todoItem)
    }

    override fun selectAll() {
        todoAdapter.selectAll=2
        selectall=!selectall

        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()
    }

    override fun cancelSelect() {
        todoAdapter.selectAll=1
        selectall=!selectall

        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()
    }

    override fun cancelDelete() {
        delete=false
        selectall=false
        todoAdapter.deleteMode=false
        todoAdapter.selectAll=1
        todoAdapter.notifyDataSetChanged()

        invalidateOptionsMenu()
    }

    fun delete(){
        for(i in 0 until todoAdapter.itemCount){
            if(todoAdapter.list[i].deleteChecked){
                toDoViewModel.delete(todoAdapter.list[i])
            }

        }
        selectall=false
        delete=false
        todoAdapter.deleteMode=false
        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()

    }

    override fun onPause() {
        super.onPause()
        Log.d("[onPause]","executed")
        cancelDelete()
    }
}