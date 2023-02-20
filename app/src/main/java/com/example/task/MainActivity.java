package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskListAdapter.OnTaskClickListener {

    private RecyclerView taskListRecyclerView;
    private TaskListAdapter taskListAdapter;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the RecyclerView
        taskListRecyclerView = findViewById(R.id.taskListRecyclerView);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskListAdapter = new TaskListAdapter(new ArrayList<>(), this);
        taskListRecyclerView.setAdapter(taskListAdapter);

        // Get the TaskViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, tasks -> {
            taskListAdapter.updateTaskList(tasks);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add_task) {
            openAddTaskActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskClick(int position) {
        Task task = taskListAdapter.getItem(position);
        openViewTaskActivity(task.getId());
    }

    private void openAddTaskActivity() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    private void openViewTaskActivity(int taskId) {
        Intent intent = new Intent(this, ViewTaskActivity.class);
        intent.putExtra("taskId", taskId);
        startActivity(intent);
    }
}

