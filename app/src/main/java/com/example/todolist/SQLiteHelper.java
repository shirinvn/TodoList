package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TABLE_TASK= "tbl_tasks";
    private static final String TAG="SqliteHelper";

    public SQLiteHelper(@Nullable Context context) {
        super(context, "db_app", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE "+ TABLE_TASK +" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT, completed BOOLEAN );");

        }catch (SQLiteException e)
        {
            Log.e(TAG,"onCreated:"+ e.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

public long addTask (Task task){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("title", task.getTitle());
    contentValues.put("completed", task.isCompleted());
     long result=   sqLiteDatabase.insert(TABLE_TASK, null,contentValues);

     sqLiteDatabase.close();


     return result;
}



public List<Task> getTaska(){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_TASK, null);

    List<Task> tasks = new ArrayList<>();
    if (cursor.moveToFirst()){

        do {
            Task task= new Task();
            task.setId(cursor.getLong(0));
            task.setTitle(cursor.getString(1));
            task.setCompleted(cursor.getInt(2) == 1);
            tasks.add(task);
        } while (cursor.moveToNext());


      }
    return tasks;
}


public int updateTask(Task task){

    SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("title", task.getTitle());
    contentValues.put("completed", task.isCompleted());
    int result=   sqLiteDatabase.update(TABLE_TASK,contentValues,"id = ?", new String[]{String.valueOf(task.getTitle())});

    sqLiteDatabase.close();
    return result;

}



public List<Task> searchInTasks(String query){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_TASK+" WHERE title LIKE '%" + query+ "%'", null);
    List<Task> tasks = new ArrayList<>();
    if (cursor.moveToFirst()){

        do {
            Task task= new Task();
            task.setId(cursor.getLong(0));
            task.setTitle(cursor.getString(1));
            task.setCompleted(cursor.getInt(2) == 1);
            tasks.add(task);
        } while (cursor.moveToNext());


    }
    return tasks;

}






public int deleteTask(Task task){

  SQLiteDatabase sqLiteDatabase = getWritableDatabase();
  int result = sqLiteDatabase.delete(TABLE_TASK, "id = ?", new String[]{String.valueOf(task.getId())});

  sqLiteDatabase.close();
  return result;
}
public void clearAllTasks(){
        try {
            SQLiteDatabase sqLiteDatabase= getWritableDatabase();
            sqLiteDatabase.execSQL("DELETE FROM "+ TABLE_TASK);

        }catch (SQLiteException e){
            Log.i(TAG,"CLearAllTasks");
        }

}


}
