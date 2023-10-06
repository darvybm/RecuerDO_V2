package com.example.recuerdo_v2.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.recuerdo_v2.entidades.Task;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> tasks;

    TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDAO = db.taskDAO();
        tasks = taskDAO.getTasks();
    }

    LiveData<List<Task>> getAllTask() {
        return tasks;
    }

    void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDAO.insert(task);
        });
    }

    void update(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDAO.updateTask(task);
        });
    }

    void delete(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDAO.deleteTask(task);
        });
    }
}
