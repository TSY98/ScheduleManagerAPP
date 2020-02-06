package com.example.schedulemanagerapp.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class NoDoneFragment extends Fragment {

    private RecyclerView showSchedule;
    private List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
    private ScheduleMsgAdapter adapter;
    private FloatingActionButton add_fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_show_schedule, container, false);
        List<ScheduleInfo> scheduleInfos = DataSupport.findAll(ScheduleInfo.class);
        scheduleInfoList.addAll(scheduleInfos);

        showSchedule = view.findViewById(R.id.recycle_showSch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        showSchedule.setLayoutManager(layoutManager);
        adapter = new ScheduleMsgAdapter(scheduleInfoList);
        showSchedule.setAdapter(adapter);



        add_fab = view.findViewById(R.id.show_sche_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
