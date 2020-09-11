package com.project.mvvm.base.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.project.mvvm.R
import com.project.mvvm.base.model.Todo
import com.project.mvvm.base.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        // intent null check & get extras
        if (intent != null && intent.hasExtra(EXTRA_TODO_TITLE)
            && intent.hasExtra(EXTRA_TODO_ID)
        ) {
            add_et_title.setText(intent.getStringExtra(EXTRA_TODO_TITLE))
            add_et_date.setText(intent.getStringExtra(EXTRA_TODO_DATE))
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

    companion object {
        const val EXTRA_TODO_TITLE = "EXTRA_TODO_TITLE"
        const val EXTRA_TODO_DATE = "EXTRA_TODO_NUMBER"
        const val EXTRA_TODO_ID = "EXTRA_TODO_ID"
    }
}