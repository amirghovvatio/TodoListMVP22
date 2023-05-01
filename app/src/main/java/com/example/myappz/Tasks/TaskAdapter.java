package com.example.myappz.Tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappz.Model.Tasks;
import com.example.myappz.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Tasks> tasks=new ArrayList<>();
    private TasksEventListener eventListener;

    public TaskAdapter(List<Tasks> tasks, TasksEventListener eventListener) {
        this.tasks = tasks;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent,false);
        return new TaskViewHolder(view);
    }
    public void deleteTasks(Tasks tasks){
        int index=this.tasks.indexOf(tasks);
        this.tasks.remove(tasks);
        notifyItemRemoved(index);
    }
    public void addTasks(Tasks tasks){
        this.tasks.add(0,tasks);
        notifyItemInserted(0);
    }
    public void setTasks(List<Tasks> tasks){
        this.tasks=tasks;
        notifyDataSetChanged();
    }
    public void updateTasks(Tasks tasks){
        int index=this.tasks.indexOf(tasks);
        this.tasks.set(index,tasks);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindTasks(tasks.get(position));
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private CheckBox chkTask;
        private ImageView imgDelete;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            chkTask=itemView.findViewById(R.id.checkBoxTaskList);
            imgDelete=itemView.findViewById(R.id.deleteTaskList);

        }
        public void bindTasks(Tasks tasks){
            chkTask.setText(tasks.getTitle());
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onDeleteClicked(tasks.getDayID(),tasks);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    eventListener.onLongClicked(tasks);
                    return false;
                }
            });
        }
    }
    public interface TasksEventListener{
        void onDeleteClicked(int dayID,Tasks tasks);
        void onLongClicked(Tasks tasks);
    }
}
