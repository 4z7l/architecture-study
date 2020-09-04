package com.project.mvvm.base.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mvvm.R
import com.project.mvvm.base.model.Todo

class TodoAdapter(val contactItemClick: (Todo) -> Unit, val contactItemLongClick: (Todo) -> Unit)
    : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todos: List<Todo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(todos[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.item_tv_title)
        private val tvDate = itemView.findViewById<TextView>(R.id.item_tv_date)
        private val tvRank = itemView.findViewById<TextView>(R.id.item_tv_rank)

        fun bind(todo: Todo) {
            tvTitle.text = todo.title
            tvDate.text = todo.date
            tvRank.text = todo.id.toString()

            itemView.setOnClickListener {
                contactItemClick(todo)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(todo)
                true
            }
        }
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

}