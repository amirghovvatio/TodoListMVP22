package com.example.myappz.Days;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappz.Model.Days;
import com.example.myappz.R;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private List<Days> daysList;
    private DayEventListener eventListener;

    public DayAdapter(List<Days> daysList, DayEventListener eventListener) {
        this.daysList = daysList;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.day_list,parent,false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.bindDays(daysList.get(position));
    }
    public void deleteDays(Days days){
        int index=daysList.indexOf(days);
        daysList.remove(days);
        notifyItemRemoved(index);
    }
    public void addDays(Days days){
        daysList.add(0,days);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        private CheckBox chkDayName;
        private TextView tvDate;
        private ImageView imdDelete;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            chkDayName=itemView.findViewById(R.id.chekcBoxDayList);
            tvDate=itemView.findViewById(R.id.tvDateDayList);
            imdDelete=itemView.findViewById(R.id.deleteDayList);
        }
        public void bindDays(Days days){
            chkDayName.setText(days.getDayName());
            tvDate.setText(days.getDate());
            imdDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onDeleteClicked(days);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    eventListener.onLongClickedUpdate(days.getDayID(),days);
                    return false;
                }
            });
        }
    }
    public interface DayEventListener{
        void onDeleteClicked(Days days);
        void onLongClickedUpdate(int dayId,Days days);
    }
}
