package com.example.myappz.Tasks;

import com.example.myappz.BasePresenter;
import com.example.myappz.BaseView;
import com.example.myappz.Model.Tasks;

import java.util.List;


public interface TaskContract {
    interface TaskView extends BaseView{
        void showTaskList(List<Tasks> tasks);
        void deleteTask(Tasks tasks);
    }
    interface TaskPresenter extends BasePresenter<TaskView>{
        void deleteTask(Tasks tasks);

    }
    
//========================================================================

    interface AddTaskView extends BaseView{
        void returnResult(int resultCode,Tasks tasks);
    }
    interface AddTaskPresenter extends BasePresenter<AddTaskView>{
        void saveChanges(String title);
    }


//========================================================================

    interface EditTaskView extends BaseView{
        void saveEditChanges(Tasks tasks);
    }

    interface EditTaskPresenter extends BasePresenter<EditTaskView>{
        void editTask(String title);
    }
}
