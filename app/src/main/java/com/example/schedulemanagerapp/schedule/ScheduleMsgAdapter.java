package com.example.schedulemanagerapp.schedule;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulemanagerapp.R;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_LONG).show();
                ScheduleInfo scheduleInfo = mScheduleMsgList.get(position);
                scheduleInfo.setDone(true);
                scheduleInfo.updateAll("mark=?", scheduleInfo.getMark());
                if (scheduleInfo.isDone()) {
                    Log.d("change-done","true");

                }else {
                    Log.d("change-nodone","false");
                }
                mScheduleMsgList.remove(position);
                notifyItemRemoved(position);//刷新被删除的地方
                notifyItemRangeChanged(position, getItemCount());
                Toast.makeText(getContext(), "完成", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mScheduleMsgList.size();
    }
}
