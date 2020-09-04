package com.project.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.mvvm.model.datasource.ToDo
import com.project.mvvm.model.repository.RoomRepository

public class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RoomRepository by lazy {
        RoomRepository(application)
    }

    fun getAll() : LiveData<List<ToDo>> {
        return repository.getAll()
    }

    fun insert(todo: ToDo) {
        repository.insert(todo)
    }

    fun update(todo: ToDo) {
        repository.update(todo)

    }

    fun delete(todo: ToDo) {
        repository.delete(todo)


    }
    fun deleteAll() {
        repository.deleteAll()


    }
    fun toggleCheckedState(todo: ToDo) {
        todo.checked = !todo.checked
        repository.update(todo)
    }

    fun toggleDeleteCheckedState(todo: ToDo) {
        todo.deleteChecked = !todo.deleteChecked
        repository.update(todo)
    }

    fun toggleDeleteCheckedStateTrue(todo: ToDo) {
        todo.deleteChecked =true
        repository.update(todo)
    }

    fun toggleDeleteCheckedStateFalse(todo: ToDo) {
        todo.deleteChecked = false
        repository.update(todo)
    }


}