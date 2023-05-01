package com.example.myappz.Tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myappz.Model.AppDataBase;
import com.example.myappz.Model.Tasks;
import com.example.myappz.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddTasksActivity extends AppCompatActivity implements TaskContract.AddTaskView {
    private TextInputEditText edtTitle;
    private int dayID;
    private TaskContract.AddTaskPresenter presenter;
    private View saveChanges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dayID=getIntent().getIntExtra("newDayId",0);
        presenter=new AddTaskPresenter(dayID, AppDataBase.getAppDataBase(this).getDao());
        edtTitle=findViewById(R.id.edtTaskAddTask);
        saveChanges=findViewById(R.id.saveTaskAddTaskActivity);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveChanges(edtTitle.getText().toString());
            }
        });

        presenter.onAttach(this);
    }

    @Override
    public void returnResult(int resultCode, Tasks tasks) {
        Intent intent=new Intent();
        intent.putExtra("extra2",tasks);
        setResult(TaskActivity.ADD_RESULT_CODE,intent);
        finish();
    }
}