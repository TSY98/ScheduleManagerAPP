package com.example.schedulemanagerapp.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schedulemanagerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ShowScheduleActivity extends AppCompatActivity {


    private RecyclerView showSchedule;
    private List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
    private ScheduleMsgAdapter adapter;
    private FloatingActionButton add_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);


        List<ScheduleInfo> scheduleInfos = DataSupport.findAll(ScheduleInfo.class);
        scheduleInfoList.addAll(scheduleInfos);

        showSchedule = findViewById(R.id.recycle_showSch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        showSchedule.setLayoutManager(layoutManager);
        adapter = new ScheduleMsgAdapter(scheduleInfoList);
        showSchedule.setAdapter(adapter);

        adapter.notifyItemInserted(scheduleInfoList.size() - 1);

        add_fab = findViewById(R.id.show_sche_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowScheduleActivity.this, AddScheduleActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
