package com.example.btl_nhom20;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.model.user.User;
import com.google.android.material.textfield.TextInputLayout;

public class register extends AppCompatActivity {
    ReponsityUser reponsity;
    EditText name, email, password;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPasswordValid;
    TextInputLayout nameError, emailError, passError;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reponsity = new ReponsityUser(this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        progressBar = findViewById(R.id.progressBar);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( SetValidation()){
                    //cắt bỏ khoảng trắng đầu và cuối
                    String strEmail = email.getText().toString().trim();
                    String strPass = password.getText().toString().trim();
                    String userName = name.getText().toString().trim();

                    User user = new User(userName,strEmail,strPass);
                    progressBar.setVisibility(View.VISIBLE);
                    if( reponsity.insert(user) ){
                        Intent intent = new Intent(register.this,login.class);
                        startActivity(intent);
                        finishAffinity();
                        progressBar.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(register.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
    }

    public boolean SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }
        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPasswordValid) {
           return true;
        }
        return false;
    }
}
