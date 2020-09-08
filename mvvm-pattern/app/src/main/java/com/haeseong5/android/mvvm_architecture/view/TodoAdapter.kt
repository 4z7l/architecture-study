package com.haeseong5.android.mvvm_architecture.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haeseong5.android.mvvm_architecture.R
import com.haeseong5.android.mvvm_architecture.model.Todo

class TodoAdapter(private val todoItems: ArrayList<Todo>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = todoItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todo = todoItems[position]
        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todo)
    }

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(todo: Todo) {

        }
    }

}