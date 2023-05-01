package com.example.myappz.Tasks;

import com.example.myappz.Model.AppDao;
import com.example.myappz.Model.Tasks;

public class EditPresenter implements TaskContract.EditTaskPresenter {
    private AppDao appDao;
    private Tasks tasks;
    private TaskContract.EditTaskView view;
    public EditPresenter(AppDao appDao,Tasks tasks) {
        this.appDao = appDao;
        this.tasks=tasks;
    }


    @Override
    public void onAttach(TaskContract.EditTaskView view) {
        this.view=view;
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public void editTask(String title) {
        tasks.setTitle(title);
        int i= appDao.updateTasks(tasks);
        if (i > -1){
            this.view.saveEditChanges(tasks);
        }
    }
}
