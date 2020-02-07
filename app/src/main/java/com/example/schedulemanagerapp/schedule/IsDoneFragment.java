package com.example.schedulemanagerapp.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.schedulemanagerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class IsDoneFragment extends Fragment {
    private RecyclerView showSchedule;
    private List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
    private ScheduleMsgIsDoneAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_show_schedule_is_done, container, false);

        List<ScheduleInfo> scheduleInfos = DataSupport.where("isDone=?", "1").find(ScheduleInfo.class);
        scheduleInfoList.addAll(scheduleInfos);
        Log.d("count", ""+scheduleInfos.size());
        //test();

        showSchedule = view.findViewById(R.id.recycle_showSch_isdone);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        showSchedule.setLayoutManager(layoutManager);
        adapter = new ScheduleMsgIsDoneAdapter(scheduleInfoList);
        showSchedule.setAdapter(adapter);




        return view;
    }
    private void test() {
//        ScheduleInfo scheduleInfo = new ScheduleInfo();
//        scheduleInfo.setScheduleTitle("dfsa");
//        scheduleInfo.setImportance(2);
        ScheduleInfo scheduleInfo1 = new ScheduleInfo();
        scheduleInfo1.setScheduleTitle("birthday");
        scheduleInfo1.setImportance(3);
        scheduleInfo1.setDate("2019年1月9号");
        scheduleInfo1.setRemind(false);
        scheduleInfo1.setDone(false);
        scheduleInfo1.setRemark("oneus");
        ScheduleInfo scheduleInfo2 = new ScheduleInfo();
        scheduleInfo2.setScheduleTitle("meeting");
        scheduleInfo2.setImportance(1);
        scheduleInfo1.setDate("2019年3月19号");
        scheduleInfo1.setRemind(false);
        scheduleInfo1.setRemark("day");
        scheduleInfo2.setDone(true);
        //scheduleInfo1.save();
        //scheduleInfo2.save();
        scheduleInfoList.add(scheduleInfo1);
        scheduleInfoList.add(scheduleInfo2);
    }
}
