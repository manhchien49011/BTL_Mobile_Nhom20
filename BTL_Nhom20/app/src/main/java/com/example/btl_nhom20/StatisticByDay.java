package com.example.btl_nhom20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.User;
import com.example.btl_nhom20.model.user.UserWorkspace;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticByDay extends AppCompatActivity {
    ReponsityUserWorkspace reponsityUserWorkspace;
    ReponsityCalendar reponsityCalendar;
    ReponsityUser reponsityUser;
    private TextView txt_statisticByDay,tv_workOnTime,tv_lateForWork,tv_offWork;
    private ImageView imgBackActivity;
    private CardView workOnTime,offWork,lateForWork;
    private int idWsp;
    private List<User> mListWorkOnTime;
    private List<User> mListLateForWork;
    private List<User> mListOffWork;
    private int dem=0, demWorkOnTime=0, demLateForWork=0, demOffWork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_by_day);

        reponsityUserWorkspace = new ReponsityUserWorkspace(this);
        reponsityUser = new ReponsityUser(this);
        reponsityCalendar = new ReponsityCalendar(this);

        txt_statisticByDay = findViewById(R.id.statisticByDay_text);
        imgBackActivity = findViewById(R.id.statisticBackActivity);
        workOnTime = findViewById(R.id.workOnTime);
        offWork = findViewById(R.id.offWork);
        lateForWork = findViewById(R.id.lateForWork);
        tv_workOnTime = findViewById(R.id.tv_workOnTime);
        tv_lateForWork = findViewById(R.id.tv_lateForWork);
        tv_offWork = findViewById(R.id.tv_offWork);


        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        idWsp = bundle.getInt("wspID");

        mListWorkOnTime = new ArrayList<>();
        mListLateForWork = new ArrayList<>();
        mListOffWork = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = "Thời gian: "+day + "/" + (month+1) + "/" + year+"-"+day + "/" + (month+1) + "/" + year;

        txt_statisticByDay.setText(dateString);



        // get list user belong to workspace
        List<UserWorkspace> listUserByWsp = reponsityUserWorkspace.getByWorkspaceId(idWsp);
        for (UserWorkspace model : listUserByWsp) {
            dem++;
        }
        dem--;

        // get list user work on time
        List<ModelCalendar> listCalendarWorkspace = reponsityCalendar.getByWorkspace(idWsp);
        for (ModelCalendar model : listCalendarWorkspace) {
            if(model.getMonth()==month+1 && model.getYear()==year && model.getDay()==day){
                if(model.getType().equals("WorkOnTime")){
                    demWorkOnTime++;
                }
                else if(model.getType().equals("LateForWork")){
                    demLateForWork++;
                }
//                else{demOffWork++;}
            }
        }

        dem -= demWorkOnTime;
        String tv1 = "Có "+String.valueOf(demWorkOnTime)+" người đi làm đúng giờ";
        tv_workOnTime.setText(tv1);

        dem -= demLateForWork;
        String tv2 = "Có "+String.valueOf(demLateForWork)+" người đi làm muộn";
        tv_lateForWork.setText(tv2);


        String tv3 = "Có "+dem+" người chưa checkin";
//        String tv3 = "Có "+String.valueOf(demOffWork)+" người chưa checkin";
        tv_offWork.setText(tv3);


        workOnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticByDay.this,WorkOnTimeActivity.class);
                Bundle bundle1 = new Bundle();
               bundle1.putInt("wspID",bundle.getInt("wspID"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        lateForWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticByDay.this,LateForWorkActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("wspID",bundle.getInt("wspID"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        offWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticByDay.this,OffWorkActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("wspID",bundle.getInt("wspID"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        imgBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}