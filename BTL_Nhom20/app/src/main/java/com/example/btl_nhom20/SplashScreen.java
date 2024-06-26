package com.example.btl_nhom20;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom20.model.user.ModelCalendar;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ModelCalendar model = new ModelCalendar();
        Log.i("CheckTypeModel", model.getType());

        imageView = findViewById(R.id.SplashLogo);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(360).withEndAction(this).setDuration(10000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        imageView.animate().rotationBy(360).withEndAction(runnable).setDuration(10000).setInterpolator(new LinearInterpolator()).start();



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,login.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

//    private void nextActivity() {
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        User user = null;
//        if(user == null){
//            Intent intent = new Intent(SplashScreen.this,login.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
//            startActivity(intent);
//        }
//    }
}