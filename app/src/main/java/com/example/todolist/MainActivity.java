package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaskAdapter taskAdapter= new TaskAdapter();
        RecyclerView recyclerView= findViewById(R.id.rv_main_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(taskAdapter);


        View addnewTaskFab= findViewById(R.id.fab_main_addNewTask );
        addnewTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDialog dialog= new TaskDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }
}