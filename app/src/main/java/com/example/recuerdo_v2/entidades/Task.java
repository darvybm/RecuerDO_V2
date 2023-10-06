package com.example.recuerdo_v2.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tarea;
    private boolean completada;

    public Task(String tarea, boolean completada) {
        this.tarea = tarea;
        this.completada = completada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", tarea='" + tarea + '\'' +
                ", completada=" + completada +
                '}';
    }
}
