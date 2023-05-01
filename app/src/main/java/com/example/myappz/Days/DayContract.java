package com.example.myappz.Days;

import com.example.myappz.BasePresenter;
import com.example.myappz.BaseView;
import com.example.myappz.Model.Days;
import com.example.myappz.Model.Tasks;

import java.util.List;

public interface DayContract {
    interface View extends BaseView{
        void getDayList(List<Days> days);
        void deleteDays(Days days);
    }

    interface Presenter extends BasePresenter<View>{
        void onDeleteClicked(Days days);
    }
    interface AddView extends BaseView{
        void returnResult(int requestCode, Days days);
    }
    interface AddPresenter extends BasePresenter<AddView>{
        void saveChanges(String dayName,String date);

    }

}
