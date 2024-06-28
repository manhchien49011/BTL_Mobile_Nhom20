package com.example.btl_nhom20;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class Calendar extends AppCompatActivity {

    ReponsityCalendar reponsityCalendar;
    ReponsityUser reponsityUser;


    private CalendarView calendar;
    private TextView selectDate;
    private SimpleDateFormat sdf;
    private ImageView btnBackPressed;
    private LinearLayout noCheckIn;
    private TextView btnCheckIn,checked,overtime;
    private ProgressBar progressBar;

    private int idWsp;
    private String uid;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private List<ModelCalendar> listUserAndWsp;



    private java.util.Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // init reponsity
        reponsityCalendar = new ReponsityCalendar(this);
        reponsityUser = new ReponsityUser(this);

        calendar = (CalendarView)findViewById(R.id.calendar);
        selectDate = findViewById(R.id.selectDate);
        btnBackPressed = findViewById(R.id.btnBackPressed);
        checked = findViewById(R.id.checked);
        noCheckIn = findViewById(R.id.no_checkin);
        btnCheckIn = findViewById(R.id.btn_checkin);
        progressBar = findViewById(R.id.progressBar);
        overtime = findViewById(R.id.overtime);


        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        idWsp = bundle.getInt("wspID");
        uid = MySharedPreferences.getInstance(this).getString("User_Id");
        Log.i("User_Workspace", uid + "_"+ idWsp);
        sdf = new SimpleDateFormat("EEEE", Locale.US);

        listUserAndWsp = reponsityCalendar.getByUserAndWorkspace(Integer.parseInt(uid), idWsp);

        mCalendar = java.util.Calendar.getInstance();
        year = mCalendar.get(java.util.Calendar.YEAR);
         month = mCalendar.get(java.util.Calendar.MONTH);
         day = mCalendar.get(java.util.Calendar.DAY_OF_MONTH);
         hour = mCalendar.get(java.util.Calendar.HOUR_OF_DAY);
         minute = mCalendar.get(java.util.Calendar.MINUTE);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(day + "/"+(month+1)+"/"+year);
            String dayOfTheWeek = sdf.format(date);
            String dateString = dayOfTheWeek + ", "+day+"/"+(month+1)+"/"+year;
            selectDate.setText(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy",Locale.US).parse(dayOfMonth + "/"+(month+1)+"/"+year);
                    String dayOfTheWeek = sdf.format(date);
                    String dateSelect = dayOfTheWeek+", "+dayOfMonth + "/"+(month+1)+"/"+year;
                    selectDate.setText(dateSelect);
                    showCheckIn(year,month,dayOfMonth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        showCheckIn(year,month,day);
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                CheckIn();
                checked.setVisibility(View.VISIBLE);
                noCheckIn.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });

        btnBackPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void CheckIn(){

        for(ModelCalendar calendar : listUserAndWsp){
            if(String.valueOf(calendar.getUser_Id()).equals(uid)){
                User user = reponsityUser.getById(Integer.parseInt(uid));
                if( (hour == 8 && minute <= 30)){
                    WorkOnTime(user);
                }
                else{
                    LateForWork(user);
                }
                break;
            }
        }


    }

    private void showCheckIn(int selectYear, int selectMonth, int selectDay){
        if(selectDay != day || selectMonth != month || selectYear != year){
            if(selectDay > day){
                Toast.makeText(getBaseContext(), "Chưa đến ngày Checkin", Toast.LENGTH_SHORT).show();

            }else if (selectDay < day){
                Toast.makeText(getBaseContext(), "Qua ngày checkin rồi", Toast.LENGTH_SHORT).show();
            }
            overtime.setVisibility(View.VISIBLE);
            checked.setVisibility(View.GONE);
            noCheckIn.setVisibility(View.GONE);
            return;
        }else{ Toast.makeText(getBaseContext(), "Checkin đi", Toast.LENGTH_SHORT).show();
            for (ModelCalendar model : listUserAndWsp){
                if(model.getDay() == day && model.getMonth() == month + 1 && model.getYear() == year){


                    if(model.getType().equals("WorkOnTime")){
                        if(day == selectDay && year == selectYear && month == selectMonth){
                            checked.setVisibility(View.VISIBLE);
                            noCheckIn.setVisibility(View.GONE);
                            overtime.setVisibility(View.GONE);
                        }else{noCheckIn.setVisibility(View.VISIBLE);
                            checked.setVisibility(View.GONE);
                            overtime.setVisibility(View.GONE);};
//                        else if(hour >= 5 && hour <= 17){
//                            noCheckIn.setVisibility(View.VISIBLE);
//                            checked.setVisibility(View.GONE);
//                            overtime.setVisibility(View.GONE);
//                        }
                    }
                    else if(model.getType().equals("LateForWork")){
                        if(day == selectDay && month== selectMonth && year == selectYear){
                            checked.setVisibility(View.VISIBLE);
                            noCheckIn.setVisibility(View.GONE);
                            overtime.setVisibility(View.GONE);
                        }else{noCheckIn.setVisibility(View.VISIBLE);
                            checked.setVisibility(View.GONE);
                            overtime.setVisibility(View.GONE);}

//                        else if(hour >= 5  && hour <=17){
//                            noCheckIn.setVisibility(View.VISIBLE);
//                            checked.setVisibility(View.GONE);
//                            overtime.setVisibility(View.GONE);
//                        }
                    }


                    else{
                        noCheckIn.setVisibility(View.GONE);
                        checked.setVisibility(View.GONE);
                        if(hour < 5 && hour > 17){
                            overtime.setVisibility(View.VISIBLE);
                        }
                    }
                    return;
                }
            }}
//        Toast.makeText(getBaseContext(), String.format("Day: %d, Month: %d, Year: %d", selectDay, selectMonth, selectYear), Toast.LENGTH_SHORT).show();
//        for (ModelCalendar model : listUserAndWsp){
//            if(model.getDay() == day && model.getMonth() == month + 1 && model.getYear() == year){
//                if(model.getType().equals("WorkOnTime")){
//                    if(day == selectDay && year == selectYear && month == selectMonth){
//                        checked.setVisibility(View.VISIBLE);
//                        noCheckIn.setVisibility(View.GONE);
//                        overtime.setVisibility(View.GONE);
//                    }
//                    else if(hour >= 5 && hour <= 17){
//                        noCheckIn.setVisibility(View.VISIBLE);
//                        checked.setVisibility(View.GONE);
//                        overtime.setVisibility(View.GONE);
//                    }
//                }
//                else if(model.getType().equals("LateForWork")){
//                    if(day == selectDay && month== selectMonth && year == selectYear){
//                        checked.setVisibility(View.VISIBLE);
//                        noCheckIn.setVisibility(View.GONE);
//                        overtime.setVisibility(View.GONE);
//                    }
//                    else if(hour >= 5  && hour <=17){
//                        noCheckIn.setVisibility(View.VISIBLE);
//                        checked.setVisibility(View.GONE);
//                        overtime.setVisibility(View.GONE);
//                    }
//                }
//                else{
//                    noCheckIn.setVisibility(View.VISIBLE);
//                    checked.setVisibility(View.GONE);
//                    if(hour < 5 && hour > 17){
//                        overtime.setVisibility(View.VISIBLE);
//                    }
//                }
//                return;
//            }
//        }
    }


    private void WorkOnTime(User user){
        ModelCalendar calendar = new ModelCalendar(Integer.parseInt(uid), idWsp,
                year, month+1, day, hour, minute, "WorkOnTime");
        calendar.setDateOfEmployment(String.format("%d/%d/%d", day, month+1, year));
        if(reponsityCalendar.update(calendar)){

            Toast.makeText(getBaseContext(), "Bạn đã checkin thêm lần nữa thành công", Toast.LENGTH_SHORT).show();
        }
        else{
            reponsityCalendar.insert(calendar);
            Toast.makeText(getBaseContext(), "Chào Ngày mới", Toast.LENGTH_SHORT).show();
        }
    }

    private void LateForWork(User user){
        ModelCalendar calendar = new ModelCalendar(Integer.parseInt(uid), idWsp,
                year, month+1, day, hour, minute, "LateForWork");
        calendar.setDateOfEmployment(String.format("%d/%d/%d", day, month+1, year));
        if(reponsityCalendar.update(calendar)){
            Toast.makeText(getBaseContext(), "Bạn checkin thêm lần nữa rồi", Toast.LENGTH_SHORT).show();
        }
        else{
            reponsityCalendar.insert(calendar);
            Toast.makeText(getBaseContext(), "Chào Ngày mới", Toast.LENGTH_SHORT).show();
        }
    }


}