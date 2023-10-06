package com.example.recuerdo_v2.adapters;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuerdo_v2.R;
import com.example.recuerdo_v2.data.TaskRepository;
import com.example.recuerdo_v2.data.TaskViewModel;
import com.example.recuerdo_v2.entidades.Task;

import java.util.List;
import java.util.function.Consumer;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ViewHolder> {

    Consumer<Task> taskConsumer;
    Consumer<Task> taskConsumerDelete;

    public Consumer<Task> getTaskConsumerDelete() {
        return taskConsumerDelete;
    }

    public void setTaskConsumerDelete(Consumer<Task> taskConsumerDelete) {
        this.taskConsumerDelete = taskConsumerDelete;
    }

    public Consumer<Task> getTaskConsumer() {
        return taskConsumer;
    }

    public void setTaskConsumer(Consumer<Task> taskConsumer) {
        this.taskConsumer = taskConsumer;
    }

    public TaskAdapter(DiffUtil.ItemCallback<Task> diff) {
        super(diff);
    }

    public static class TaskDiff extends DiffUtil.ItemCallback<Task> {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == (newItem.getId());
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = getItem(position);

        holder.bind(task);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTitle;
        public CheckBox completedCheckBox;
        private ImageButton btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskTitle);
            completedCheckBox = itemView.findViewById(R.id.checkBox);
            btnEliminar = itemView.findViewById(R.id.deleteButton);
        }

        public void bind(Task task) {

            taskTitle.setText(task.getTarea());
            completedCheckBox.setChecked(task.isCompletada());

            if (task.isCompletada()) {
                taskTitle.setPaintFlags(taskTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskTitle.setPaintFlags(taskTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            completedCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (taskConsumer != null) {
                        task.setCompletada(!task.isCompletada());
                        Log.i("TAG", task.getTarea() + "-" + task.isCompletada());
                        taskConsumer.accept(task);
                    }
                    else {
                        Log.i("TAG", "No working");
                    }
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (taskConsumerDelete != null) {
                        taskConsumerDelete.accept(task);
                    }
                }
            });
        }
    }
}
