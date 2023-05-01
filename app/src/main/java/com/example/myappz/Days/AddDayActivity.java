package com.example.myappz.Days;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myappz.Model.AppDataBase;
import com.example.myappz.Model.Days;
import com.example.myappz.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddDayActivity extends AppCompatActivity implements DayContract.AddView {
    private TextInputEditText edtDayName;
    private TextInputEditText edtDate;
    private View saveChangesBtn;
    private DayContract.AddPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        presenter=new AddDayPresenter(AppDataBase.getAppDataBase(this).getDao());

        edtDayName=findViewById(R.id.edtDayAddDay);
        edtDate=findViewById(R.id.edtDateAddDay);
        saveChangesBtn=findViewById(R.id.saveDayAddDayActivity);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveChanges(edtDayName.getText().toString(),edtDate.getText().toString());
            }
        });

        presenter.onAttach(this);
    }

    @Override
    public void returnResult(int requestCode, Days days) {
        Intent intent=new Intent();
        intent.putExtra("extra1",days);
        setResult(requestCode,intent);
        finish();
    }
}