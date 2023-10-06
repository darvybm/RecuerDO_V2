package com.example.recuerdo_v2.data;

import androidx.lifecycle.LiveData;
import com.example.recuerdo_v2.entidades.Task;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();
}
