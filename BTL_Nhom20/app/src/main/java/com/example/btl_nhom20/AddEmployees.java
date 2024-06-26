package com.example.btl_nhom20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom20.Reponsity.ReponsitoryNotification;
import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.Notification;
import com.example.btl_nhom20.model.user.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddEmployees extends AppCompatActivity {
    int userCurrentId;
    ReponsityUser reponsityUser;
    ReponsityCalendar reponsityCalendar;
    ReponsitoryNotification reponsitoryNotification;

    private AutoCompleteTextView  email;
    private Button AddEmployees;
    private ImageView back_workspace;
    private boolean isEmailValid;
    private TextInputLayout  emailError;
    private List<String> strEmail;
    private List<User> users;
    private List<String> strEmailEmployee;
    private int idWsp;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employees);

        //init reponsity
        reponsityUser = new ReponsityUser(this);
        reponsityCalendar = new ReponsityCalendar(this);
        reponsitoryNotification = new ReponsitoryNotification(this);
        userCurrentId = Integer.parseInt(MySharedPreferences.getInstance(getBaseContext()).getString("User_Id"));

        email = (AutoCompleteTextView) findViewById(R.id.email);
        back_workspace = (ImageView) findViewById(R.id.back_workspace);
        AddEmployees = (Button) findViewById(R.id.AddEmployees);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        progressBar = findViewById(R.id.progressBar);
        strEmail = new ArrayList<>();
        users = new ArrayList<>();
        strEmailEmployee = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        idWsp = bundle.getInt("wspID");

        users = reponsityUser.getAll();
        for (User u : users){
            strEmail.add(u.getEmail());
        }


        //get User belong to workspace
        List<ModelCalendar> calendars = reponsityCalendar.getByWorkspace(idWsp);
        for(ModelCalendar calendar : calendars){
            User user = reponsityUser.getById(calendar.getUser_Id());
            strEmailEmployee.add(user.getEmail());
        }

        ArrayAdapter<String> adapterEmail = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, strEmail);
        email.setThreshold(2);
        email.setAdapter(adapterEmail);

       AddEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SetValidation()){
                    progressBar.setVisibility(View.VISIBLE);
                    String uidReceiver = "";
                    String nameReceiver ="";
                    User userCurrent = reponsityUser.getById(userCurrentId);
                    String emailSender = userCurrent.getEmail();
                    String idSender = userCurrentId + "";
                    String emailReceiver = email.getText().toString().trim();

                    if(emailSender.equals(emailReceiver)){
                        Toast.makeText(getApplicationContext(),"Bạn không thể mời chính mình !!!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String message = " đã mời bạn tham gia workspace có ID:" +String.valueOf(idWsp);
                        for(User user : users){
                            if(user.getEmail().equals(emailReceiver)){
                                uidReceiver = user.getUid() + "";
                                nameReceiver = user.getUsername();
                                break;
                            }
                        }
                        if(nameReceiver != "" && uidReceiver != ""){
                            Notification notification = new Notification(Integer.parseInt(idSender),Integer.parseInt(uidReceiver),
                                    message,idWsp,emailSender,0,0);

                            if(reponsitoryNotification.insert(notification)){
                                Toast.makeText(getApplicationContext(),"Đã mời thành công !!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddEmployees.this);
                                alertDialog.setTitle("Thông báo");
                                alertDialog.setIcon(R.drawable.logo);
                                alertDialog.setMessage("Mời thành công. Bạn có muốn tiếp tục mời nhân viên ??");

                                alertDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        email.setText("",false);
                                    }
                                });
                                alertDialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                alertDialog.show();
                            }
                    }

                    }
                }
            }
        });

        back_workspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean SetValidation() {
        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }
        else  if(!checkEmailUser()){
            emailError.setError(getResources().getString(R.string.email_exist));
            isEmailValid = false;
            }
        else if(checkEmailEmployee()){
            emailError.setError(getResources().getString(R.string.employee_exist));
            isEmailValid = false;
        }
        else{
            emailError.setErrorEnabled(false);
            isEmailValid= true;
        }
        if(isEmailValid){
            return true;
        }
         return false;
    }
    private boolean checkEmailUser(){
        for(String str : strEmail){
            if(str.equals(email.getText().toString())){
                return true;
            }
        }
        return false;
    }
    private boolean checkEmailEmployee(){
        for(String str : strEmailEmployee){
            if(str.equals(email.getText().toString())){
                return true;
            }
        }
        return false;
    }
}
