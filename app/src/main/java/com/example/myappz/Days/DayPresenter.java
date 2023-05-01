package com.example.myappz.Days;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.Days;

public class DayPresenter implements DayContract.Presenter{
    private DayContract.View view;
    private AppDao appDao;

    public DayPresenter( AppDao appDao) {
        this.appDao = appDao;
    }

    @Override
    public void onAttach(DayContract.View view) {
        this.view=view;
        this.view.getDayList(appDao.getAllDays());
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public void onDeleteClicked(Days days) {
        int i= appDao.deleteDays(days);
        if (i>0){
            this.view.deleteDays(days);
        }
    }
}
