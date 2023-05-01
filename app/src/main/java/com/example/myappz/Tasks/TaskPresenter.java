package com.example.myappz.Tasks;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.Tasks;

public class TaskPresenter implements TaskContract.TaskPresenter{
    private AppDao appDao;
    private int dayId;
    private TaskContract.TaskView view;
    public TaskPresenter(int dayID,AppDao appDao){
        this.appDao=appDao;
        this.dayId=dayID;
    }

    @Override
    public void onAttach(TaskContract.TaskView view) {
        this.view=view;
        this.view.showTaskList(appDao.getAllTasks(dayId));
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public void deleteTask(Tasks tasks) {
        int id=appDao.deleteTasks(tasks);
        if (id > 0){
            this.view.deleteTask(tasks);
        }
    }
}
