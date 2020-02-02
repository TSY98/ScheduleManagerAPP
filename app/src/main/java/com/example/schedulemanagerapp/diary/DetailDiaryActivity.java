package com.example.schedulemanagerapp.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedulemanagerapp.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;


/**
 * 查看日记详情，内设删除和编辑跳转按钮
 */
public class DetailDiaryActivity extends AppCompatActivity {

    private TextView date;
    private TextView theme;
    private TextView detail;
    private Button btn_back;
    private Button btn_delete;
    private Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_diary);

        Intent intent = getIntent();
        final DiaryContent diaryContent= (DiaryContent) intent.getSerializableExtra("DiaryContent");

        date = findViewById(R.id.dairyDate);
        theme = findViewById(R.id.dairyTheme);
        detail = findViewById(R.id.detailText);

        date.setText(diaryContent.getDate());
        theme.setText(diaryContent.getTitle());
        detail.setText(diaryContent.getContent());

        btn_back = findViewById(R.id.back_button);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //删除日记
        btn_delete = findViewById(R.id.delete_button);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomDialog customDialog = new CustomDialog(DetailDiaryActivity.this);
                customDialog.setMassage("确认删除该条日记吗？").setConfirm("确认", new CustomDialog.IonConfirmListener() {
                    @Override
                    public void OnConfirm(CustomDialog dialog) {
                        DataSupport.deleteAll(DiaryContent.class,"date=?",diaryContent.getDate());
                        Toast.makeText(DetailDiaryActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(DetailDiaryActivity.this, ShowDiaryActivity.class);
                        startActivity(intent1);
                    }
                }).setCancle("取消", new CustomDialog.IonCancleListener() {
                    @Override
                    public void OnCancle(CustomDialog dialog) {
                        customDialog.cancel();
                    }
                }).show();



            }
        });

        //编辑日记
        btn_edit = findViewById(R.id.Edit_button);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailDiaryActivity.this, EditDiaryActivity.class);
                intent1.putExtra("editDairy", diaryContent);
                startActivity(intent1);
                finish();
            }
        });

    }
}
