package com.example.myappz.Days;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myappz.Model.AppDataBase;
import com.example.myappz.Model.Days;
import com.example.myappz.R;
import com.example.myappz.Tasks.TaskActivity;

import java.util.List;

public class DaysActivity extends AppCompatActivity implements DayContract.View, DayAdapter.DayEventListener {
    private DayAdapter adapter;
    private RecyclerView rvDayList;
    private View addDays;
    public static final int REQUEST_CODE=100;

    private DayContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter=new DayPresenter(AppDataBase.getAppDataBase(this).getDao());
        rvDayList=findViewById(R.id.rvDayList);
        rvDayList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        addDays=findViewById(R.id.addDayBtn);
        addDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DaysActivity.this,AddDayActivity.class);
                startActivityIfNeeded(intent,REQUEST_CODE);
            }
        });

        presenter.onAttach(this);
    }

    @Override
    public void getDayList(List<Days> days) {
        adapter=new DayAdapter(days,this);
        rvDayList.setAdapter(adapter);
    }

    @Override
    public void deleteDays(Days days) {
        adapter.deleteDays(days);
    }

    @Override
    public void onDeleteClicked(Days days) {
            presenter.onDeleteClicked(days);
    }

    @Override
    public void onLongClickedUpdate(int dayId, Days days) {
        Intent intent=new Intent(this, TaskActivity.class);
        intent.putExtra("dayId",dayId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == 1 && data != null){
            Days days=data.getParcelableExtra("extra1");
            adapter.addDays(days);
        }
    }
}