package com.example.schedulemanagerapp.schedule;

import android.content.Intent;
import android.os.Bundle;
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

        List<ScheduleInfo> scheduleInfos = DataSupport.where("isDone=?", "true").find(ScheduleInfo.class);
        scheduleInfoList.addAll(scheduleInfos);

        showSchedule = view.findViewById(R.id.recycle_showSch_isdone);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        showSchedule.setLayoutManager(layoutManager);
        adapter = new ScheduleMsgIsDoneAdapter(scheduleInfoList);
        showSchedule.setAdapter(adapter);




        return view;
    }

}
