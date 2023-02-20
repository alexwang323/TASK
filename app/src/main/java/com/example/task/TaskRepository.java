package com.example.task;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        AppDatabase taskDatabase = AppDatabase.getInstance(application);
        taskDAO = taskDatabase.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<Task> getTaskById(int id) {
        return taskDAO.getTaskById(id);
    }

    public void insertTask(Task task) {
        new InsertTaskAsyncTask(taskDAO).execute(task);
    }

    public void updateTask(Task task) {
        new UpdateTaskAsyncTask(taskDAO).execute(task);
    }

    public void deleteTask(Task task) {
        new DeleteTaskAsyncTask(taskDAO).execute(task);
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private InsertTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.insertTask(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private UpdateTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.updateTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private DeleteTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.deleteTask(tasks[0]);
            return null;
        }
    }
}
