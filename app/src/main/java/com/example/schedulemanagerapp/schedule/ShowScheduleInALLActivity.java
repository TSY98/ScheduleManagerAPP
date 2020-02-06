package com.example.schedulemanagerapp.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.schedulemanagerapp.R;

public class ShowScheduleInALLActivity extends AppCompatActivity implements View.OnClickListener {

    private IsDoneFragment isDoneFragment;
    private NoDoneFragment noDoneFragment;

    private TextView btn_nodone;
    private TextView btn_isdone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule_in_all);

        btn_isdone = findViewById(R.id.isDone_button);
        btn_nodone = findViewById(R.id.noDone_button);
        btn_isdone.setOnClickListener(this);
        btn_nodone.setOnClickListener(this);



        initIsDoneFragement();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.isDone_button:
                initIsDoneFragement();
                break;
            case R.id.noDone_button:
                initNoDoneFragement();
                break;
        }

    }

    private void initNoDoneFragement() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (noDoneFragment == null) {
            noDoneFragment = new NoDoneFragment();
            fragmentTransaction.add(R.id.main_fragment, noDoneFragment);
        }
        //noDoneFragment = new NoDoneFragment();
        //fragmentTransaction.replace(R.id.main_fragment, noDoneFragment);
        hideFragement(fragmentTransaction);
        fragmentTransaction.show(noDoneFragment);
        fragmentTransaction.commit();
    }
    private void initIsDoneFragement() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (isDoneFragment == null) {
            isDoneFragment = new IsDoneFragment();
            fragmentTransaction.add((R.id.main_fragment), isDoneFragment);
        }
        hideFragement(fragmentTransaction);
        //isDoneFragment = new IsDoneFragment();
        //fragmentTransaction.replace(R.id.main_fragment, isDoneFragment);
        fragmentTransaction.show(isDoneFragment);
        fragmentTransaction.commit();
    }
    private void hideFragement(FragmentTransaction transaction){
        if (isDoneFragment != null) {
            transaction.hide(isDoneFragment);
        }
        if (noDoneFragment != null) {
            transaction.hide(noDoneFragment);
        }
    }

}
