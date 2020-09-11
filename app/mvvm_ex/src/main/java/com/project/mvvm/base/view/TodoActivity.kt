package com.project.mvvm.base.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mvvm.R
import com.project.mvvm.base.model.Todo
import com.project.mvvm.base.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // Set contactItemClick & contactItemLongClick lambda
        val adapter = TodoAdapter({ contact ->
            // put extras of contact info & start AddActivity
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_TODO_TITLE, contact.title)
            intent.putExtra(AddActivity.EXTRA_TODO_DATE, contact.date)
            intent.putExtra(AddActivity.EXTRA_TODO_ID, contact.id)
            startActivity(intent)
        }, { contact->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(this)
        main_recycleview.adapter = adapter
        main_recycleview.layoutManager = lm
        main_recycleview.setHasFixedSize(true)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        todoViewModel.getAll().observe(this, Observer<List<Todo>> { todos ->
            adapter.setTodos(todos!!)
        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(todo: Todo) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                todoViewModel.delete(todo)
            }
        builder.show()
    }
}