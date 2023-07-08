package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements TaskDialog.AddNewTaskCallback{
    private SQLiteHelper sqLiteHelper;
    private TaskAdapter taskAdapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView= findViewById(R.id.rv_main_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(taskAdapter);

        sqLiteHelper= new SQLiteHelper(this);
        List<Task> tasks= sqLiteHelper.getTaska();
        taskAdapter.addItems( tasks);

        View addnewTaskFab= findViewById(R.id.fab_main_addNewTask );
        addnewTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDialog dialog= new TaskDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void onNewTask(Task task) {
        long taskId= sqLiteHelper.addTask(task);
        if (taskId != -1){
            task.setId(taskId);
            taskAdapter.addItem(task);
        }else {
            Log.e("Main Activity", "item Not Inserted");
        }

    }
}