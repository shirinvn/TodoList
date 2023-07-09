package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddTaskDialog extends DialogFragment {
    private AddNewTaskCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback= (AddNewTaskCallback) context;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_task_add,null,false);

        TextInputEditText titleET= view.findViewById(R.id.et_dialog_title);
        TextInputLayout inputlayout= view.findViewById(R.id.etl_dialog_title);
        View saveBtn= view.findViewById(R.id.btn_dialog_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.length() > 0){
                    Task task = new Task();
                    task.setTitle(titleET.getText().toString());
                    task.setCompleted(false);
                    callback.onNewTask(task);
                    dismiss();

                }else {
                    inputlayout.setError("عنوان نباید خالی باشد");
                }
            }
        });
        builder.setView(view);
        return builder.create();
    }


    public interface AddNewTaskCallback{
        void onNewTask(Task task);
    }
}
