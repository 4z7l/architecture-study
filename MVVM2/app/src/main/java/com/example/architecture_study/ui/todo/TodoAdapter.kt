package com.example.architecture_study.ui.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_study.R
import com.example.architecture_study.data.Todo
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var mTodoItems : List<Todo> = listOf()
    private var mitemClickListener: OnItemClickListener?=null

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.mitemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent,false)

        return ViewHolder(view, mitemClickListener)
    }

    override fun getItemCount(): Int {
        return mTodoItems.size
    }

    fun getItem(position: Int): Todo {
        return mTodoItems.get(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTodoItems[position])
    }


    class ViewHolder(itemView: View, itemClickListener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.item_todo_title
        private val description = itemView.item_todo_description
        private val date = itemView.item_todo_date

        init {
            itemView.setOnClickListener{
                itemClickListener?.onItemClick(adapterPosition)
            }
        }

        fun bind(item:Todo){
            title.text = item.title
            description.text = item.description
            date.text = item.date.toDateString("yyyy-MM-dd HH:mm")
        }

        fun Long.toDateString(format : String): String {
            val simpleDateFormat = SimpleDateFormat(format)
            return simpleDateFormat.format((Date(this)))
        }
    }

    fun setTodoItems(items : List<Todo>){
        this.mTodoItems = items
        notifyDataSetChanged()
    }
}