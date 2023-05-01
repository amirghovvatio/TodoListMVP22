package com.example.myappz.Tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.AppDataBase;
import com.example.myappz.Model.Tasks;
import com.example.myappz.R;
import com.google.android.material.textfield.TextInputEditText;

public class EditTaskDialog extends DialogFragment implements TaskContract.EditTaskView {
    private EditCallBack callBack;
    private View btnEdit;
    private TextInputEditText edtEdit;
    private Tasks tasks;
    private TaskContract.EditTaskPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBack= (EditCallBack) context;
        tasks=getArguments().getParcelable("loop");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.edit_dialog,null,false);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(view);

        presenter=new EditPresenter(AppDataBase.getAppDataBase(getContext()).getDao(),tasks);
        edtEdit=view.findViewById(R.id.edtEditTasks);
        btnEdit=view.findViewById(R.id.btnSaveEdit);
        edtEdit.setText(tasks.getTitle());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.editTask(edtEdit.getText().toString());
                    dismiss();

            }
        });

        presenter.onAttach(this);

        return builder.create();
    }

    @Override
    public void saveEditChanges(Tasks tasks) {
        callBack.onEdited(tasks);

    }

    public interface EditCallBack{
        void onEdited(Tasks tasks);
    }
}
