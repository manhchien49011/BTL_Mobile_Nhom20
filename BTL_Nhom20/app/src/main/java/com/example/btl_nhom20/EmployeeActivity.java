package com.example.btl_nhom20;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.adapter.EmployeeAdapter;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.User;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {
    ReponsityCalendar reponsityCalendar;
    ReponsityUser reponsityUser;
    private RecyclerView rcv_employee;
    private List<User> mListUsers;
    private EmployeeAdapter mEmployeeAdapter;
    private ImageView btnBack;
    private TextView tv_nameWorkspace,tv_emailWorkspace;
    private int idWsp;
   // private FirebaseDatabase database ;
    //private DatabaseReference reference ;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        reponsityCalendar = new ReponsityCalendar(this);
        reponsityUser = new ReponsityUser(this);

        rcv_employee = findViewById(R.id.rcv_employee);
        tv_nameWorkspace = findViewById(R.id.tv_nameWorkspace);
        tv_emailWorkspace = findViewById(R.id.tv_emailWorkspace);
        btnBack = findViewById(R.id.btnBackPressed);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        idWsp = bundle.getInt("wspID");
        if (savedInstanceState != null) {
            idWsp = savedInstanceState.getInt("idWsp");
        }
        mListUsers = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_employee.setLayoutManager(linearLayoutManager);

        mEmployeeAdapter = new EmployeeAdapter(mListUsers,getApplicationContext());
        rcv_employee.setAdapter(mEmployeeAdapter);

        uId = MySharedPreferences.getInstance(this).getString("User_Id");

        getListEmployee();

        //xíu comment nha
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference();
//        reference.child("Workspaces").child(String.valueOf(idWsp)).child("admin").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                reference.child("Users").child((String) task.getResult().getValue()).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if(task.isSuccessful()){
//                            tv_emailWorkspace.setText(String.valueOf(task.getResult().getValue()));
//                        }
//                    }
//                });
//                reference.child("Users").child((String) task.getResult().getValue()).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if(task.isSuccessful()){
//                            tv_nameWorkspace.setText(String.valueOf(task.getResult().getValue()));
//                        }
//                    }
//                });
//            }
//        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getListEmployee() {
        List<ModelCalendar> modelCalendars = reponsityCalendar.getByWorkspace(idWsp);
        for (ModelCalendar model : modelCalendars) {
            mListUsers.add(reponsityUser.getById(model.getUser_Id()));
        }
        mEmployeeAdapter.notifyDataSetChanged();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference();
////        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        reference.child("Workspaces").child(String.valueOf(idWsp)).child("Employees").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(mListUsers != null){
//                    mListUsers.clear();
//                }
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                    User user = dataSnapshot.getValue(User.class);
//                    mListUsers.add(user);
//                }
//                mEmployeeAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(),"Get list employees fail",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("idWsp", idWsp);
    }
}