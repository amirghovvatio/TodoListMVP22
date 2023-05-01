package com.example.myappz.Tasks;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.Tasks;

public class AddTaskPresenter implements TaskContract.AddTaskPresenter{
    private TaskContract.AddTaskView view;
    private int dayId;
    private AppDao appDao;

    public AddTaskPresenter(int dayId, AppDao appDao) {
        this.dayId = dayId;
        this.appDao = appDao;
    }

    @Override
    public void onAttach(TaskContract.AddTaskView view) {
        this.view=view;
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public void saveChanges(String title) {
        Tasks tasks=new Tasks();
        tasks.setTitle(title);
        tasks.setDayID(dayId);
        long id=appDao.addTasks(tasks);
        if (id > 0){
            tasks.setTaskID((int) id);
        }
        this.view.returnResult(TaskActivity.ADD_RESULT_CODE,tasks);
    }
}
