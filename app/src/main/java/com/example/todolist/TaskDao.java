package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    long addTask(Task task);


    @Delete
    int deleteTask(Task task);

    @Update
    int updateTask(Task task);



    @Query("SELECT * FROM tbl_tasks")
    List<Task> getTasks();


    @Query("SELECT * FROM tbl_tasks WHERE title LIKE '%' ||  :query  || '%'")
    List<Task> search(String query);


    @Query("DELETE FROM tbl_tasks")
    void deleteAll();

}
