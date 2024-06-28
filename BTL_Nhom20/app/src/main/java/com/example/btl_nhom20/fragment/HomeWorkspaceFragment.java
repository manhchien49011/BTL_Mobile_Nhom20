package com.example.btl_nhom20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.btl_nhom20.AddEmployees;
import com.example.btl_nhom20.Calendar;
import com.example.btl_nhom20.EmployeeActivity;
import com.example.btl_nhom20.ListResignationLetters;
import com.example.btl_nhom20.R;
import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.Reponsity.ReponsityWorkspace;
import com.example.btl_nhom20.ResignationLetterActivity;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.StatisticByDay;
import com.example.btl_nhom20.SummaryEmployeeActivity;
import com.example.btl_nhom20.SummaryForAdmin;
import com.example.btl_nhom20.WorkspaceActivityAdmin;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.User;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class HomeWorkspaceFragment extends Fragment {
    public HomeWorkspaceFragment() {
        // Required empty public constructor
    }
    private ReponsityWorkspace mReponsityWorkspace;
    private ReponsityUser mReponsityUser;
    private ReponsityCalendar reponsityCalendar;
    private LinearLayout calendarWorkspace,statisticByDay,add_employees,resignation_letter,list_employee;
    private TextView tv_name_workspace,tv_email_workspace,summary_year;
    private CardView cv_administrator,browse_app,single_newspaper,staff,summary;
    private  WorkspaceActivityAdmin mWorkspaceActivityAdmin;
    private ImageView btnBackPressed;

    private String uid;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private LinearLayout noCheckIn;
    private TextView btnCheckIn;
    private java.util.Calendar mCalendar;
   // private FirebaseDatabase database;
   // private DatabaseReference reference;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_workspace, container, false);
        //khởi tạo reponsity
        mReponsityWorkspace = new ReponsityWorkspace(getContext());
        mReponsityUser = new ReponsityUser(getContext());
        reponsityCalendar = new ReponsityCalendar(getContext());

        calendarWorkspace = view.findViewById(R.id.calendarWorkspace);
        statisticByDay = view.findViewById(R.id.statistic);
        add_employees = view.findViewById(R.id.add_employee);
        resignation_letter = view.findViewById(R.id.resignnation_letter);
        tv_name_workspace = view.findViewById(R.id.tv_name_workspace);
        tv_email_workspace = view.findViewById(R.id.tv_email_workspace);
        cv_administrator = view.findViewById(R.id.cv_administrator);
        browse_app = view.findViewById(R.id.browse_app);
        single_newspaper = view.findViewById(R.id.single_newspaper);
        list_employee = view.findViewById(R.id.list_employee);
        staff = view.findViewById(R.id.Staff);
        progressBar = view.findViewById(R.id.progressBar);
        btnBackPressed = view.findViewById(R.id.btnBackPressed);
        summary_year = view.findViewById(R.id.summary_year);
        summary = view.findViewById(R.id.summary);

        //
        mCalendar = java.util.Calendar.getInstance();
        year = mCalendar.get(java.util.Calendar.YEAR);
        summary_year.setText("Nhìn lại năm "+year);
        noCheckIn = view.findViewById(R.id.no_checkin);
        btnCheckIn = view.findViewById(R.id.btn_checkin);
        //

        mCalendar = java.util.Calendar.getInstance();
        year = mCalendar.get(java.util.Calendar.YEAR);
        month = mCalendar.get(java.util.Calendar.MONTH);
        day = mCalendar.get(java.util.Calendar.DAY_OF_MONTH);
        hour = mCalendar.get(java.util.Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(java.util.Calendar.MINUTE);

        //
        mWorkspaceActivityAdmin = (WorkspaceActivityAdmin) getActivity();

        tv_name_workspace.setText(mWorkspaceActivityAdmin.getName());
        tv_email_workspace.setText(mWorkspaceActivityAdmin.getEmail());

        uid = MySharedPreferences.getInstance(getContext()).getString("User_Id");
        showCheckIn();


//        progressBar.setVisibility(View.VISIBLE);
        if(uid.equals(mWorkspaceActivityAdmin.getUserIdAdmin())){
            single_newspaper.setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    noCheckIn.setVisibility(View.GONE);
//                    progressBar.setVisibility(View.GONE);
                }
            },50000);
        }
        else {
            if(hour < 8 || hour > 17 ){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    noCheckIn.setVisibility(View.GONE);
//                    progressBar.setVisibility(View.GONE);
                }
            },50000);
            }
            cv_administrator.setVisibility(View.GONE);
            browse_app.setVisibility(View.GONE);
            single_newspaper.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        single_newspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), ListResignationLetters.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckIn();
            }
        });
        calendarWorkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), Calendar.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        statisticByDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), StatisticByDay.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        add_employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), AddEmployees.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), EmployeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        list_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), EmployeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        resignation_letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), ResignationLetterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnBackPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkspaceActivityAdmin.finish();
            }
        });
        // duyệt đơn
        browse_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), ListResignationLetters.class);
                Bundle bundle = new Bundle();
                bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uid.equals(mWorkspaceActivityAdmin.getUserIdAdmin())){
                    // tổng kết của admin
                    Intent intent = new Intent(getActivity().getApplication(), SummaryForAdmin.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity().getApplication(), SummaryEmployeeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("wspID",mWorkspaceActivityAdmin.getIdWsp());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void CheckIn(){
        User user = mReponsityUser.getById(Integer.parseInt(uid));

        if( (hour == 8 && minute <= 30)){
            WorkOnTime(user);
            Toast.makeText(getContext(),"Bạn đã checkin thành công",Toast.LENGTH_SHORT).show();
        }
        else{
            LateForWork(user);
            Toast.makeText(getContext(),"Bạn đã checkin muộn rồi",Toast.LENGTH_SHORT).show();
        }

        //Lấy ra danh sách user thuộc workspace

//        List<ModelCalendar> modelCalendars = reponsityCalendar.getByWorkspace(mWorkspaceActivityAdmin.getIdWsp());
//        for (ModelCalendar model : modelCalendars){
//            if(String.valueOf(model.getUser_Id()).equals(uid) ){
//                if( (hour == 8 && minute <= 15)){
//                    WorkOnTime(user);
//                    Toast.makeText(getContext(),"Bạn đã checkIn thành công",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    LateForWork(user);
//                    Toast.makeText(getContext(),"Bạn đã checkIn thành công",Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }
//

    }

    private void showCheckIn(){

        List<ModelCalendar> modelCalendars = reponsityCalendar.getByUserAndWorkspace(Integer.parseInt(uid) ,mWorkspaceActivityAdmin.getIdWsp());

        for (ModelCalendar model : modelCalendars){
            if(model.getYear()==year && model.getMonth()==month+1 && model.getDay()==day){
                if(model.getType().isEmpty()){
                    noCheckIn.setVisibility(View.GONE);
                }
                else{
                   noCheckIn.setVisibility(View.VISIBLE);
                }
                return;
            }
        }

        noCheckIn.setVisibility(View.VISIBLE);
    }

    private void WorkOnTime(User user){
        ModelCalendar calendar = new ModelCalendar(Integer.parseInt(uid), mWorkspaceActivityAdmin.getIdWsp(),
                year, month+1, day, hour, minute, "WorkOnTime");
        calendar.setDateOfEmployment(String.format("%d/%d/%d", day, month+1, year));
        if(reponsityCalendar.update(calendar)){
            Toast.makeText(getActivity(), "Bạn đã checkin thêm lần nữa thành công", Toast.LENGTH_SHORT).show();
        }
        else{
            reponsityCalendar.insert(calendar);
            Toast.makeText(getActivity(), "Chào Ngày mới", Toast.LENGTH_SHORT).show();
        }
    }

    private void LateForWork(User user){
        ModelCalendar calendar = new ModelCalendar(Integer.parseInt(uid), mWorkspaceActivityAdmin.getIdWsp(), year,
                month + 1, day, hour, minute, "LateForWork");
        calendar.setDateOfEmployment(String.format("%d/%d/%d", day, month + 1, year));
        if(reponsityCalendar.update(calendar)){
            Toast.makeText(getActivity(), "Bạn đã checkin thêm lần nữa rồi", Toast.LENGTH_SHORT).show();
        }
        else{
            reponsityCalendar.insert(calendar);
            Toast.makeText(getActivity(), "Chào Ngày mới", Toast.LENGTH_SHORT).show();
        }
    }

}