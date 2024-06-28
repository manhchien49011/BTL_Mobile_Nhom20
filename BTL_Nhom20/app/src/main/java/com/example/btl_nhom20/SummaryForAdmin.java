package com.example.btl_nhom20;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.Reponsity.ReponsityWorkspace;
import com.example.btl_nhom20.adapter.SummaryForAdminAdapter;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.MonthlySummary;
import com.example.btl_nhom20.model.user.User;
import com.example.btl_nhom20.model.user.UserWorkspace;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SummaryForAdmin extends AppCompatActivity {
    HashMap<Integer, List<MonthlySummary>> hashMap = new HashMap<>();

    ReponsityWorkspace reponsityWorkspace;;
    ReponsityCalendar reponsityCalendar;
    ReponsityUser reponsityUser;
    ReponsityUserWorkspace reponsityUserWorkspace;
    RecyclerView recyclerViewSummaryForAdmin;
    private ImageView btnBack;

    // variables for features
    private String workspaceId;
    private String year;
    String adminId;
    private int totalWorkOnTimes;
    private int totalLateForWorks;
    private int totalOffWorks;
    private Calendar calendar;
    private ArrayList<User> listUsers;
    private ArrayList<MonthlySummary> listMonthlySummary;
    private SummaryForAdminAdapter summaryForAdminAdapter;

    // constant variable
    private final int[] dayOfMonths = {31,29,31,30,31,30,31,31,30,31,30,31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_for_admin);

        reponsityWorkspace = new ReponsityWorkspace(this);
        reponsityCalendar = new ReponsityCalendar(this);
        reponsityUser = new ReponsityUser(this);
        reponsityUserWorkspace = new ReponsityUserWorkspace(this);

        // set variable
        listUsers = new ArrayList<User>();
        listMonthlySummary = new ArrayList<MonthlySummary>();

        // get data in bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        workspaceId = String.valueOf(bundle.getInt("wspID"));

        // set by ID
        recyclerViewSummaryForAdmin = findViewById(R.id.recyclerViewSummaryForAdmin);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseContext(),
                DividerItemDecoration.VERTICAL);
        recyclerViewSummaryForAdmin.addItemDecoration(dividerItemDecoration);
        btnBack = findViewById(R.id.btnBackPressed);

        // get date
        calendar = Calendar.getInstance();
        year = String.valueOf(calendar.get(Calendar.YEAR));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSummaryForAdmin.setLayoutManager(linearLayoutManager);

        // set adapter
        summaryForAdminAdapter = new SummaryForAdminAdapter(getBaseContext(), hashMap);
        recyclerViewSummaryForAdmin.setAdapter(summaryForAdminAdapter);

        // proccessing
        getDataFromFirebaseDatabase();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getDataFromFirebaseDatabase() {
        adminId = reponsityUserWorkspace.getUserIdAdminByWorkspaceId(Integer.parseInt(workspaceId)) + "";

        // get list the employee
        List<UserWorkspace> listUserWorkspaces = reponsityUserWorkspace.getByWorkspaceId(Integer.parseInt(workspaceId));
        for(UserWorkspace userWorkspace : listUserWorkspaces){
            if(!userWorkspace.isAdmin())
                listUsers.add(reponsityUser.getById(userWorkspace.getUserId()));
        }
        // get list calendar by year
        List<ModelCalendar> listCalendarByYear = reponsityCalendar.getByYear(Integer.parseInt(year));



        for(int i=1; i<=12; i++){
            List<MonthlySummary> listMonthlySummary = new ArrayList<>();
            for(User user : listUsers){
                totalLateForWorks = 0;
                totalWorkOnTimes = 0;
                totalOffWorks = 0;
                for(ModelCalendar model : listCalendarByYear){
                    if(model.getMonth() == i && model.getUser_Id() == user.getUid()){
                        if(model.getType().equals("WorkOnTime")){
                            totalWorkOnTimes ++;
                        }
                        else if(model.getType().equals("LateForWork")){
                            totalLateForWorks ++;
                        }
                    }
                }
                totalOffWorks = dayOfMonths[i-1] - totalWorkOnTimes - totalLateForWorks;
                MonthlySummary monthlySummary = new MonthlySummary(i, totalWorkOnTimes, totalLateForWorks, totalOffWorks, user.getUsername());
                listMonthlySummary.add(monthlySummary);
            }
            hashMap.put(i, listMonthlySummary);
        }


        summaryForAdminAdapter.notifyDataSetChanged();
    }
}