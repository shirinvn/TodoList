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

public class EditTaskDialog extends DialogFragment {
    private EditTaskCallback callback;
    private  String title;
    private Task task;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback= (EditTaskCallback) context;
        task= getArguments().getParcelable("task");
        if (task == null){
            dismiss();
        }



    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_task_edit,null,false);

      final   TextInputEditText titleET= view.findViewById(R.id.et_dialogedit_title);
    titleET.setText(task.getTitle());
       final TextInputLayout inputlayout= view.findViewById(R.id.etl_dialogedit_title);
        View saveBtn= view.findViewById(R.id.btn_dialogedit_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.length() > 0){
                    task.setTitle(titleET.getText().toString());
                    callback.onEditTask(task);
                    dismiss();

                }else {
                    inputlayout.setError("عنوان نباید خالی باشد");
                }
            }
        });
        builder.setView(view);
        return builder.create();
    }


    public interface EditTaskCallback{
        void onEditTask(Task task);
    }
}
