package com.example.myappz.Days;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.Days;

public class AddDayPresenter implements DayContract.AddPresenter{

    private AppDao appDao;
    private DayContract.AddView view;

    public AddDayPresenter( AppDao appDao) {
        this.appDao = appDao;
    }

    @Override
    public void saveChanges(String dayName, String date) {
        Days days=new Days();
        days.setDayName(dayName);
        days.setDate(date);
        long id = appDao.addDays(days);
        if (id > 0){
            days.setDayID((int) id);
        }
        this.view.returnResult(1,days);
    }

    @Override
    public void onAttach(DayContract.AddView view) {
        this.view=view;
    }

    @Override
    public void onDetach() {
        this.view=null;
    }
}
