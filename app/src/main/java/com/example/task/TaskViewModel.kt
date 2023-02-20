package com.example.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class TaskViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val taskRepository: TaskRepository

    init {
        taskRepository = TaskRepository(application)
    }

    val allTasks: LiveData<List<Task>>
        get() = taskRepository.allTasks

    fun getTaskById(id: Int): LiveData<Task> {
        return taskRepository.getTaskById(id)
    }

    fun insertTask(task: Task?) {
        taskRepository.insertTask(task)
    }

    fun updateTask(task: Task?) {
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task?) {
        taskRepository.deleteTask(task)
    }
}




