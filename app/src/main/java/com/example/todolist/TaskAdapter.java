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
    private TaskItemListener listener;

    public TaskAdapter (TaskItemListener listener){

        this.listener = listener;
    }

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

    public void updateItem(Task task){
        for (int i =0 ; i< tasks.size();i++){
            if (task.getId()== tasks.get(i).getId()){
                tasks.set(i,task);
                notifyItemChanged(i);
            }
        }
    }

    public void addItems(List<Task> tasks){
        this.tasks.addAll(tasks);
    notifyDataSetChanged();
    }



    public void deleteItem(Task task){
        for ( int i =0; i < tasks.size(); i++){
            if (tasks.get(i).getId()== task.getId()){
                tasks.remove(i);
                notifyItemRemoved(i);
                break;
            }

        }
    }



    public void clearItems(){
        this.tasks.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTask(List<Task> tasks){
        this.tasks= tasks;
        notifyDataSetChanged();
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
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteButtonClick(task);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongPress(task);
                    return false;
                }
            });

        }
    }

    public  interface  TaskItemListener{
        void  onDeleteButtonClick(Task task);
        void  onItemLongPress(Task task);
    }





}
