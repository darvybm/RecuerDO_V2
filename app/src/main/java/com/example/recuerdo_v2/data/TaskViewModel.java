package com.example.recuerdo_v2.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recuerdo_v2.entidades.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private final LiveData<List<Task>> tasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        tasks = repository.getAllTask();
    }

    public LiveData<List<Task>> getAllTasks() {return  tasks;}
    public void insert(Task task) {repository.insert(task);}
    public void update(Task task) {repository.update(task);}
    public void delete(Task task) {repository.delete(task);}
}
