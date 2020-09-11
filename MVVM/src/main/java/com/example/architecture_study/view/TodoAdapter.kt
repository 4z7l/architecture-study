package com.example.architecture_study.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_study.R
import com.example.architecture_study.model.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

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
        viewHolder.bind(todos[position], position)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.item_tv_title)
        private val tvDate = itemView.findViewById<TextView>(R.id.item_tv_date)
        private val tvRank = itemView.findViewById<TextView>(R.id.item_tv_rank)
        private val checkBox = itemView.findViewById<CheckBox>(R.id.cb_todo)

        fun bind(todo: Todo, position: Int) {
            tvTitle.text = todo.title
            tvDate.text = todo.date
            tvRank.text = (position+1).toString()

            itemView.setOnClickListener {
                contactItemClick(todo)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(todo)
                true
            }

            itemView.cb_todo.setOnCheckedChangeListener { compoundButton, isChecked ->
                itemView.cb_todo.isChecked = isChecked
            }
        }
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

}