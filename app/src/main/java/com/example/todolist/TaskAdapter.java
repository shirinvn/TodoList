package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter  extends RecyclerView.Adapter <TaskAdapter.TaskViewHolder> {


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
