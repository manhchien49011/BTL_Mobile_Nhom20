package com.example.btl_nhom20;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.adapter.EmployeeAdapter;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.User;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LateForWorkActivity extends AppCompatActivity {
    ReponsityCalendar reponsityCalendar = new ReponsityCalendar(this);
    ReponsityUser reponsityUser = new ReponsityUser(this);
    private RecyclerView rcv_lateForWork;
    private EmployeeAdapter mLateForWorkAdapter;
    private ImageView btnBack;


    private List<User> mListLateForWork;
    private TextView tv_dateNow,tv_amount;
    private int idWsp;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late_for_work);

        rcv_lateForWork = findViewById(R.id.rcv_employee);
        tv_dateNow = findViewById(R.id.tv_dateNow);
        tv_amount = findViewById(R.id.amount);
        btnBack = findViewById(R.id.btnBackPressed);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        String strDate = day+"/"+(month+1)+"/"+year;

        tv_dateNow.setText(strDate);

        mListLateForWork = new ArrayList<>();

        idWsp = bundle.getInt("wspID");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_lateForWork.setLayoutManager(linearLayoutManager);

        mLateForWorkAdapter = new EmployeeAdapter(mListLateForWork,getApplicationContext());
        rcv_lateForWork.setAdapter(mLateForWorkAdapter);

        getListLateForWork();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String amount = mListLateForWork.size() + " người";
                tv_amount.setText(amount);
            }
        },300);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void getListLateForWork(){
        List<ModelCalendar> calendars = new ArrayList<>();
        calendars = reponsityCalendar.getByWorkspace(idWsp);
        for(ModelCalendar model : calendars){
            if(model.getYear()==year && model.getMonth()==month+1 &&
                    model.getDay()==day && model.getType().equals("LateForWork")){
                mListLateForWork.add(reponsityUser.getById(model.getUser_Id()));
            }
        }
        mLateForWorkAdapter.notifyDataSetChanged();

    }

}