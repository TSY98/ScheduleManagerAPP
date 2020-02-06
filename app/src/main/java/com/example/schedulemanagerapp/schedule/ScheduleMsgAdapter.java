package com.example.schedulemanagerapp.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulemanagerapp.R;

import java.util.List;

public class ScheduleMsgAdapter extends RecyclerView.Adapter<ScheduleMsgAdapter.ViewHolder> {
    private List<ScheduleInfo> mScheduleMsgList;


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView importance;
        Button done;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.show_sche_title);
            date = itemView.findViewById(R.id.show_sche_date);
            importance = itemView.findViewById(R.id.show_sche_importance);
            done = itemView.findViewById(R.id.show_sche_done);
        }
    }

    public ScheduleMsgAdapter(List<ScheduleInfo> msgList) {
        mScheduleMsgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recycle_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleInfo scheduleInfo = mScheduleMsgList.get(position);
        if (scheduleInfo.getImportance()==1){
            holder.importance.setText("重要");
        }else if (scheduleInfo.getImportance()==2){
            holder.importance.setText("一般");
        }else {
            holder.importance.setText("悠闲");
        }

        holder.date.setText(scheduleInfo.getDate());
        holder.title.setText(scheduleInfo.getScheduleTitle());
    }

    @Override
    public int getItemCount() {
        return mScheduleMsgList.size();
    }
}
