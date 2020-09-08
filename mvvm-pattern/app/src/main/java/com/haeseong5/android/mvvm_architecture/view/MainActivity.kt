package com.haeseong5.android.mvvm_architecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.haeseong5.android.mvvm_architecture.R
import com.haeseong5.android.mvvm_architecture.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private lateinit var mTodoViewModel: TodoViewModel
//    private lateinit var mTodoListAdater: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_floating_action_button.setOnClickListener {
            showDialog()
        }
    }
    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            TodoViewModel::class.java)
        mTodoViewModel.getTodoList().observe(this, Observer {
//            mTodoListAdapter.setTodoItems(it)
        })
    }

    private fun showDialog(){
        alert {
            customView {
                verticalLayout {
                    padding = dip(16)

                    textView(R.string.title)
                    editText {
                        hint = context.getString(R.string.title_hint)
                    }
                    textView(R.string.description)
                    editText {
                        hint = context.getString(R.string.description_hint)
                    }
                }
            }
            yesButton {  }
            noButton {  }
        }.show()
    }
}