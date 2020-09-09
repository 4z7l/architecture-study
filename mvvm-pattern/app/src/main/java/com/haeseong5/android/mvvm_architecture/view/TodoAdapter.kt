package com.haeseong5.android.mvvm_architecture.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haeseong5.android.githubwithrx.src.kotlin.rx.AutoClearedDisposable
import com.haeseong5.android.mvvm_architecture.R
import com.haeseong5.android.mvvm_architecture.model.TodoModel
import com.haeseong5.android.mvvm_architecture.util.TodoListDiffCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
//private var todoItems: ArrayList<Todo>
class TodoAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var todoModelItems: List<TodoModel> = listOf()
    internal val disposables = AutoClearedDisposable(context as AppCompatActivity)

    interface OnTodoItemClickListener {
        fun onTodoItemClick(i : Int)
        fun onTodoItemLongClick(position: Int)
    }

    var listener: OnTodoItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = todoModelItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TodoViewHolder).bind(todoModelItems[position])
    }

    fun setTodoItems(todoModelItems: ArrayList<TodoModel>) {
        val diffCallback = TodoListDiffCallback(this.todoModelItems, todoModelItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.todoModelItems = todoModelItems
        diffResult.dispatchUpdatesTo(this)

        disposables.addDisposable(
            Observable.just(todoModelItems)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .map { DiffUtil.calculateDiff(TodoListDiffCallback(this.todoModelItems, todoModelItems))}
            .subscribe(
                { this.todoModelItems = todoModelItems
                    it.dispatchUpdatesTo(this) },
                {
                    it.printStackTrace()
                }
            )
        )

    }

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(todoModel: TodoModel) {
            itemView.item_todo_tv_title.text = todoModel.title
            itemView.item_todo_tv_descriptions.text = todoModel.descriptions
            val date = SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA).format(todoModel.createdDate)
            itemView.item_todo_tv_createdDate.text = date
        }
    }

}