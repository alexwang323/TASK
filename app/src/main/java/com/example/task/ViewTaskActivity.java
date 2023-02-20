package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class ViewTaskActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        // Get the task ID from the intent
        int taskId = getIntent().getIntExtra("taskId", -1);

        // Get the TaskViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Observe the task LiveData object
        taskViewModel.getTaskById(taskId).observe(this, task -> {
            // Update the UI with the task details
            if (task != null) {
                TextView tvName = findViewById(R.id.tvName);
                tvName.setText(task.getName());

                TextView tvDescription = findViewById(R.id.tvDescription);
                tvDescription.setText(task.getDescription());

                TextView tvDueDate = findViewById(R.id.tvDueDate);
                tvDueDate.setText(task.getDueDate());
            }
        });
    }
}
