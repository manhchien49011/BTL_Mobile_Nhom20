package com.example.btl_nhom20;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.Reponsity.ReponsityWorkspace;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.adapter.SummaryEmployeeAdapter;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.MonthlySummary;
//import com.google.firebase.database.DatabaseReference;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SummaryEmployeeActivity extends AppCompatActivity {
    List<ModelCalendar> calendarsUserAndWsp;
    List<ModelCalendar> calendars = new ArrayList<>();
    ReponsityWorkspace reponsityWorkspace;
    ReponsityCalendar reponsityCalendar;
    ReponsityUserWorkspace reponsityUserWorkspace;
    private java.util.Calendar mCalendar;

    private TextView tv_offWork,tv_lateForWork,tv_workOnTime,tv_nameWorkspace,tv_numberOfWork,tv_dateOfEmployment;
    private TextView header_summary;
    private RecyclerView rcv_monthlySummary;
    private SummaryEmployeeAdapter mSummaryAdapter;
    private List<MonthlySummary> monthlySummaryList;

    private ImageView btnBackPressed;
    //private DatabaseReference reference;

    private int year;
    private int month;
    private int day;
    private int idWsp;
    private String uid;
    private long diffDays;
    private int countOffWork;
    private int totalWorkOnTime,totalLateForWork,totalOffWork;
    private int[] arrWorkOnTime = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] arrlateForWork = {0,0,0,0,0,0,0,0,0,0,0,0};
    private String[] arrDate ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_employee);

        reponsityWorkspace = new ReponsityWorkspace(this);
        reponsityCalendar = new ReponsityCalendar(this);
        reponsityUserWorkspace = new ReponsityUserWorkspace(this);


        tv_numberOfWork = findViewById(R.id.numberOfWork);
        tv_offWork = findViewById(R.id.tv_offWork);
        tv_lateForWork = findViewById(R.id.lateForWork);
        tv_workOnTime = findViewById(R.id.workOnTime);
        tv_nameWorkspace = findViewById(R.id.name_workspace);
        tv_dateOfEmployment = findViewById(R.id.dateOfEmployment);
        btnBackPressed = findViewById(R.id.btnBackPressed);
        rcv_monthlySummary = findViewById(R.id.rcv_monthlySummary);
        header_summary = findViewById(R.id.header_summary);
        // get date
        mCalendar = java.util.Calendar.getInstance();
        year = mCalendar.get(java.util.Calendar.YEAR);
        month = mCalendar.get(java.util.Calendar.MONTH);
        day = mCalendar.get(java.util.Calendar.DAY_OF_MONTH);
        String date;
        if(month+1<10){
             date = year + "-0"+(month+1) +"-"+day;
        }
        else{
            date = year + "-"+(month+1) +"-"+day;
        }
        String header = "Tổng kết năm "+year;
        header_summary.setText(header);
        //set list monthly summary
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_monthlySummary.setLayoutManager(linearLayoutManager);
        monthlySummaryList = new ArrayList<>();
        mSummaryAdapter = new SummaryEmployeeAdapter(getApplicationContext(),monthlySummaryList);
        rcv_monthlySummary.setAdapter(mSummaryAdapter);
        //back
        btnBackPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // get bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        idWsp = bundle.getInt("wspID");
        uid = MySharedPreferences.getInstance(this).getString("User_Id");
        Log.i("User_Workspace", uid + "_"+ idWsp);
        tv_nameWorkspace.setText(reponsityWorkspace.getById(idWsp).getNameWorkspace());

        getMonthlySummary();
        // date of employment
        String dateOfWork;
        String dateOfEmployment = "Ngày vào làm : "+ calendarsUserAndWsp.get(0).getDateOfEmployment();
        tv_dateOfEmployment.setText(dateOfEmployment);
        String[] separated = calendarsUserAndWsp.get(0).getDateOfEmployment().split("/");
        arrDate = new String[]{separated[0],separated[1],separated[2]};
        if(separated[1].length()==1){
            dateOfWork = separated[2]+"-0"+separated[1]+"-"+separated[0];
        }
        else{
            dateOfWork = separated[2]+"-"+separated[1]+"-"+separated[0];
        }
        LocalDate d1 = LocalDate.parse(dateOfWork, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        diffDays = diff.toDays();
        if(String.valueOf(diffDays).equals("0")){
            diffDays = 1;
        }
        tv_numberOfWork.setText(String.valueOf(diffDays));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] day;
                if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
                    day = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                }
                else{
                    day = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                }
                MonthlySummary monthlySummary = null;
                for(int i = Integer.valueOf(arrDate[1]);i<=(month+1);i++){
                    if(String.valueOf(i).equals(arrDate[1])){
                        day[i-1] -= Integer.parseInt(arrDate[0]);
                    }
                    countOffWork = day[i-1] - arrWorkOnTime[i-1]-arrlateForWork[i-1];
                    monthlySummary = new MonthlySummary((i),arrWorkOnTime[i-1],arrlateForWork[i-1],countOffWork);
                    monthlySummaryList.add(monthlySummary);

                }

                mSummaryAdapter.notifyDataSetChanged();

                for(int i = 0;i<=11;i++){
                    totalWorkOnTime += arrWorkOnTime[i];
                }
                for(int i = 0;i<=11;i++){
                    totalLateForWork += arrlateForWork[i];
                }
                tv_workOnTime.setText(String.valueOf(totalWorkOnTime));
                tv_lateForWork.setText(String.valueOf(totalLateForWork));
                totalOffWork = (int) diffDays - totalWorkOnTime - totalLateForWork;
                tv_offWork.setText(String.valueOf(totalOffWork));


            }
        },300);


    }
     private  void getMonthlySummary(){
        int[] day;
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
             day = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        }
        else{
             day = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        }
        calendarsUserAndWsp = reponsityCalendar.getByUserAndWorkspace(Integer.parseInt(uid), idWsp);
        String[] separated = calendarsUserAndWsp.get(0).getDateOfEmployment().split("/");
         for(int i = Integer.valueOf( separated[1]);i<=(month+1);i++){
             int j = 1;

             if(String.valueOf(i).equals(separated[1])){
                 j = Integer.parseInt(separated[0]);
             }
             CountWorkOnTimeAndLateForWork(j,day[i-1],i);
         }


    }

    private  void CountWorkOnTimeAndLateForWork(int j,int day,int i){
        List<ModelCalendar> calendarsByWsp = reponsityCalendar.getByWorkspace(idWsp);

        for(;j<= day;j++){
            int finalJ = j;
            int finalI = i;
            for(ModelCalendar model : calendarsByWsp){
                if(model.getYear()==year && model.getMonth() == finalI && model.getDay() == finalJ){
                    if(model.getUser_Id() == Integer.parseInt(uid)){
                        if(model.getType().equals("WorkOnTime")){
                            arrWorkOnTime[finalI-1] +=1;
                        }
                        else if(model.getType().equals("LateForWork")){
                            arrlateForWork[finalI-1] +=1;
                        }
                        break;
                    }
                }
            }
        }
    }
}