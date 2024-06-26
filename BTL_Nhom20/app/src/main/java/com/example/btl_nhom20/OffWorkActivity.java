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
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OffWorkActivity extends AppCompatActivity {
    ReponsityCalendar reponsityCalendar = new ReponsityCalendar(this);
    ReponsityUser reponsityUser = new ReponsityUser(this);
    private RecyclerView rcv_offWork;
    private EmployeeAdapter mOffWorkAdapter;
    private ImageView btnBack;

    private List<User> mListOffWork;
    private int idWsp;
    private TextView tv_dateNow,tv_amount;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_work);

        rcv_offWork = findViewById(R.id.rcv_employee);
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

        mListOffWork = new ArrayList<>();

        idWsp = bundle.getInt("wspID");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_offWork.setLayoutManager(linearLayoutManager);

        mOffWorkAdapter = new EmployeeAdapter(mListOffWork,getApplicationContext());
        rcv_offWork.setAdapter(mOffWorkAdapter);

        getListOffWork();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String amount = mListOffWork.size() + " người";
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

    private void getListOffWork(){
        List<ModelCalendar> calendars = new ArrayList<>();
        calendars = reponsityCalendar.getByWorkspace(idWsp);
        for(ModelCalendar model : calendars){
            if(model.getYear()==year && model.getMonth()==month+1 &&
                    model.getDay()==day && !model.getType().equals("WorkOnTime") &&
            !model.getType().equals("LateForWork")){
                mListOffWork.add(reponsityUser.getById(model.getUser_Id()));
            }
        }
        mOffWorkAdapter.notifyDataSetChanged();

    }
}