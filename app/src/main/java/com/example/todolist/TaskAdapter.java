package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter  extends RecyclerView.Adapter <TaskAdapter.TaskViewHolder> {

    private List<Task> tasks= new ArrayList<>();

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.bindTask(tasks.get(position));
    }
    public void addItem (Task task){
        tasks.add(0,task);
        notifyItemChanged(0);
    }

    public void addItems(List<Task> tasks){
        this.tasks.addAll(tasks);
    notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private View deleteBtn;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox= itemView.findViewById(R.id.checkBox_task);
            deleteBtn= itemView.findViewById(R.id.btn_task_delete);
        }



        public void bindTask(Task task){
            checkBox.setChecked(task.isCompleted());
            checkBox.setText(task.getTitle());

        }
    }



}