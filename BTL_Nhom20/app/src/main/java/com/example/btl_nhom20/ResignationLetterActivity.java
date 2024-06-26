package com.example.btl_nhom20;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.btl_nhom20.Reponsity.ReponsitoryLetter;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.Reponsity.ReponsityWorkspace;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.model.letter.Letter;
import com.example.btl_nhom20.model.user.User;
import com.example.btl_nhom20.model.user.Workspace;
import com.example.btl_nhom20.types.TypeOfLetter;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ResignationLetterActivity extends AppCompatActivity {
    ReponsityUser reponsityUser;
    ReponsitoryLetter reponsitoryLetter;
    ReponsityWorkspace reponsityWorkspace;
    ReponsityUserWorkspace reponsityUserWorkspace;
    TextInputEditText textInputEditTextReasonLetter;
    TextView textViewNameLeader;
    AutoCompleteTextView autoCompleteTextViewTypeLetter, autoCompleteTextViewTimeLetter;
    Button btnSubmitLetter;
    Calendar calendar;

    private TypeOfLetter txtTypeOfLetter;
    private String txtDateOfResignation = "";
    private String txtReasonResignation = "";
    private String nameLeader = "Đỗ Mạnh Chiến";
    private ImageView back_pressed;
    private Letter letter;
    private ArrayList<User> users;
    private int idWsp;

    private String[] listTypeNameLetters = {
        "Nghỉ phép",
        "Nghỉ phép nửa ngày",
        "Nghỉ thai sản",
        "Nghỉ không lương",
        "Làm việc tại nhà",
        "Đi học, đào tạo (Do công ty yêu cầu)",
        "Đi công tác",
        "Đi gặp Khách hàng/LV bên ngoài cty",
        "Nghỉ phép và Làm việc tại nhà nửa ngày",
        "Xin đi làm muộn",
        "Xin về sớm"
    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resignation_letter);

        reponsityUser = new ReponsityUser(getBaseContext());
        reponsitoryLetter = new ReponsitoryLetter(getBaseContext());
        reponsityWorkspace = new ReponsityWorkspace(getBaseContext());
        reponsityUserWorkspace = new ReponsityUserWorkspace(getBaseContext());


        // Find and assign variable by ID
        btnSubmitLetter = findViewById(R.id.buttonSubmitLetter);
        textViewNameLeader = findViewById(R.id.textViewNameLeader);
        textViewNameLeader.setText(nameLeader);

        textInputEditTextReasonLetter = findViewById(R.id.textInputEditTextReasonLetter);
        back_pressed = findViewById(R.id.btnBackPressed);

        autoCompleteTextViewTypeLetter = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewTypeLetter);
        autoCompleteTextViewTimeLetter = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewTimeLetter);

        // setup some features before run the activity

        back_pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //// set for button
        btnSubmitLetter.setEnabled(false);
        btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.black_gray));

        //// fill list type of letters
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.textViewListNameTypeLetters, listTypeNameLetters);
        autoCompleteTextViewTypeLetter.setAdapter(adapter);


        // set on event for listener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                autoCompleteTextViewTimeLetter.setText(day + "/" + month + "/" + year);
            }
        };

        autoCompleteTextViewTimeLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), dateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });


        textInputEditTextReasonLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (
                    autoCompleteTextViewTypeLetter.getText().toString().length() > 0 &&
                    autoCompleteTextViewTimeLetter.getText().toString().length() > 0 &&
                    textInputEditTextReasonLetter.getText().toString().length() > 0
                ) {
                    btnSubmitLetter.setEnabled(true);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.green_3));
                } else {
                    btnSubmitLetter.setEnabled(false);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.black_gray));
                }
            }
        });

        autoCompleteTextViewTypeLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (
                        textInputEditTextReasonLetter.getText().toString().length() > 0 &&
                        autoCompleteTextViewTimeLetter.getText().toString().length() > 0 &&
                        autoCompleteTextViewTypeLetter.getText().toString().length() > 0
                ) {
                    btnSubmitLetter.setEnabled(true);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.green_3));
                } else {
                    btnSubmitLetter.setEnabled(false);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.black_gray));
                }
            }
        });

        autoCompleteTextViewTimeLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (
                        textInputEditTextReasonLetter.getText().toString().length() > 0 &&
                        autoCompleteTextViewTypeLetter.getText().toString().length() > 0 &&
                        autoCompleteTextViewTimeLetter.getText().toString().length() > 0
                ) {
                    btnSubmitLetter.setEnabled(true);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.green_3));
                } else {
                    btnSubmitLetter.setEnabled(false);
                    btnSubmitLetter.setBackgroundTintList(ContextCompat.getColorStateList(ResignationLetterActivity.this, R.color.black_gray));
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        idWsp = bundle.getInt("wspID");

        Workspace workspace = reponsityWorkspace.getById(idWsp);
        int idUserAdmin = reponsityUserWorkspace.getUserIdAdminByWorkspaceId(idWsp);
        User userAdmin = reponsityUser.getById(idUserAdmin);
        textViewNameLeader.setText(userAdmin.getUsername());

        btnSubmitLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                    autoCompleteTextViewTypeLetter.getText().toString().length() > 0 &&
                    autoCompleteTextViewTimeLetter.getText().toString().length() > 0 &&
                    textInputEditTextReasonLetter.getText().toString().length() > 0
                ) {
                    int indexTypeOfLetter = Arrays.asList(listTypeNameLetters).indexOf(autoCompleteTextViewTypeLetter.getText().toString());

                    txtTypeOfLetter = TypeOfLetter.values()[indexTypeOfLetter];
                    txtDateOfResignation = autoCompleteTextViewTimeLetter.getText().toString();
                    txtReasonResignation = textInputEditTextReasonLetter.getText().toString();

                    String userId = MySharedPreferences.getInstance(getBaseContext()).getString("User_Id");
                    User user = reponsityUser.getById(Integer.parseInt(userId));
                    letter = new Letter(
                            txtTypeOfLetter,
                            txtDateOfResignation,
                            txtReasonResignation,
                            idWsp, userId, user.getUsername() );
                    if(reponsitoryLetter.insert(letter)){
                        Intent intent = new Intent(ResignationLetterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                    else{
                        Toast.makeText(ResignationLetterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
    }
} );
    }}