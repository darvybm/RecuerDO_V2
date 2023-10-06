package com.example.recuerdo_v2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuerdo_v2.adapters.TaskAdapter;
import com.example.recuerdo_v2.data.TaskViewModel;
import com.example.recuerdo_v2.entidades.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecyclerToDoActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();
    Button btnAddTaskModal;
    TaskAdapter taskAdapter;
    ConstraintLayout banner;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_to_do);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        btnAddTaskModal = findViewById(R.id.btnAddTaskModal);
        banner = findViewById(R.id.constraintLayout);

        btnAddTaskModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog();
            }
        });

        int spanCount = 1;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 2;
            banner.setVisibility(View.GONE);
        }

        taskAdapter = new TaskAdapter(new TaskAdapter.TaskDiff());
        taskAdapter.setTaskConsumer(new Consumer<Task>() {
            @Override
            public void accept(Task task) {
                Log.i("TAG", task.toString());
                taskViewModel.update(task);
            }
        });

        taskAdapter.setTaskConsumerDelete(new Consumer<Task>() {
            @Override
            public void accept(Task task) {
                showDeleteConfirmationDialog(task);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));
        recyclerView.setAdapter(taskAdapter);

        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                taskAdapter.submitList(tasks);
            }
        });
    }

    private void showDeleteConfirmationDialog(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_delete_task, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        Button btnConfirm = dialogView.findViewById(R.id.btnConfirmar);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskViewModel.delete(task);
                dialog.dismiss();
            }
        });

        Button btnCancel = dialogView.findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void mostrarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        final EditText editTextTask = dialogView.findViewById(R.id.textDeleteDialog);

        Button btnAddTask = dialogView.findViewById(R.id.btnConfirmar);
        Button btnClose = dialogView.findViewById(R.id.btnCancelar);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarea = editTextTask.getText().toString();
                if (!tarea.isEmpty()) {
                    // Agregar la tarea a la lista y notificar al adaptador
                    taskViewModel.insert(new Task(tarea, false));
                    dialog.dismiss();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}