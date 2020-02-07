package com.example.schedulemanagerapp.schedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schedulemanagerapp.R;
import com.suke.widget.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText title;
    private RadioButton imp, mid, easy;
    private EditText remark;
    private com.suke.widget.SwitchButton switchButton;
    private Button chooseDate;
    private Button back, done;
    Calendar calendar= Calendar.getInstance(Locale.CHINA);
    private int importance = 2;
    private Boolean remind = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        title = findViewById(R.id.edit_sche_title);
        imp = findViewById(R.id.edit_sche_radio_imp);
        mid = findViewById(R.id.edit_sche_radio_mid);
        mid.setChecked(true);
        easy = findViewById(R.id.edit_sche_radio_easy);
        remark = findViewById(R.id.edit_sche_remark);
        switchButton = findViewById(R.id.switch_button);
        chooseDate = findViewById(R.id.sche_edit_time);
        back = findViewById(R.id.edit_sche_back_button);
        done = findViewById(R.id.edit_sche_edit_done);

        final ScheduleInfo scheduleInfo = new ScheduleInfo();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        final Date date = new Date(System.currentTimeMillis());
        String dateFormat = formatter.format(date);
        chooseDate.setText(dateFormat.substring(0,11));
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(AddScheduleActivity.this, 3, chooseDate, calendar);
                Toast.makeText(AddScheduleActivity.this, "成功选择" + chooseDate.getText(), Toast.LENGTH_LONG).show();
            }
        });

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                remind=switchButton.isChecked();
            }
        });

        imp.setOnClickListener(this);
        mid.setOnClickListener(this);
        easy.setOnClickListener(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=1;
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                final Date date1 = new Date(System.currentTimeMillis());
                String dateFormat1 = formatter1.format(date1);
                scheduleInfo.setMark(dateFormat1);
                if (!title.getText().toString().isEmpty()) {
                    scheduleInfo.setScheduleTitle(title.getText().toString());
                } else {
                    Toast.makeText(AddScheduleActivity.this, "请填写标题", Toast.LENGTH_LONG).show();
                    flag = 0;
                }
                scheduleInfo.setImportance(importance);
                if (!remark.getText().toString().isEmpty()) {
                    scheduleInfo.setRemark(remark.getText().toString());
                }
                scheduleInfo.setDate(chooseDate.getText().toString());
                scheduleInfo.setRemind(remind);
                scheduleInfo.setDone(false);
                if(flag==1){
                    scheduleInfo.save();
                    //跳转到显示界面
                    Intent intent = new Intent(AddScheduleActivity.this, ShowScheduleInALLActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddScheduleActivity.this, "成功插入", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

    }

    public static void showDatePickerDialog(Activity activity, int themeResId, final Button tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_sche_radio_imp:
                imp.setChecked(true);
                mid.setChecked(false);
                easy.setChecked(false);
                importance = 1;
                break;
            case R.id.edit_sche_radio_mid:
                imp.setChecked(false);
                mid.setChecked(true);
                easy.setChecked(false);
                importance = 2;
                break;
            case R.id.edit_sche_radio_easy:
                imp.setChecked(false);
                mid.setChecked(false);
                easy.setChecked(true);
                importance = 3;
                break;

        }

    }
}

