package com.example.myappz.Tasks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.AppDataBase;
import com.example.myappz.Model.Tasks;
import com.example.myappz.R;

import java.util.List;

public class TaskActivity extends AppCompatActivity implements TaskContract.TaskView, TaskAdapter.TasksEventListener, EditTaskDialog.EditCallBack {
    private int dayId;
    private RecyclerView rvTaskList;
    private TaskAdapter adapter;
    private View addTaskBtn;
    private TaskContract.TaskPresenter presenter;
    private static final int REQUEST_CODE=120;
    public static final int ADD_RESULT_CODE=3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        dayId=getIntent().getIntExtra("dayId",0);
        presenter=new TaskPresenter(dayId, AppDataBase.getAppDataBase(this).getDao());


        rvTaskList=findViewById(R.id.rvTaskActivity);
        rvTaskList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));


        addTaskBtn=findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaskActivity.this,AddTasksActivity.class);
                intent.putExtra("newDayId",dayId);
                startActivityIfNeeded(intent,REQUEST_CODE);
            }
        });
        presenter.onAttach(this);
    }

    @Override
    public void showTaskList(List<Tasks> tasks) {
        adapter=new TaskAdapter(tasks,this);
        rvTaskList.setAdapter(adapter);
        adapter.setTasks(tasks);
    }

    @Override
    public void deleteTask(Tasks tasks) {
        adapter.deleteTasks(tasks);
    }

    @Override
    public void onDeleteClicked(int dayID, Tasks tasks) {
        presenter.deleteTask(tasks);
    }

    @Override
    public void onLongClicked( Tasks tasks) {
        EditTaskDialog editDialog=new EditTaskDialog();
        Bundle bundle=new Bundle();
        bundle.putParcelable("loop",tasks);
        editDialog.setArguments(bundle);
        editDialog.show(getSupportFragmentManager(),null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == ADD_RESULT_CODE && data != null){
            Tasks tasks=data.getParcelableExtra("extra2");
            adapter.addTasks(tasks);
        }
    }

    @Override
    public void onEdited(Tasks tasks) {
        adapter.updateTasks(tasks);
    }
}