package com.example.btl_nhom20.fragment;


import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.DnsResolver;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Create_workspace;
import com.example.btl_nhom20.NotificationActivity;
import com.example.btl_nhom20.R;
import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUser;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.Reponsity.ReponsityWorkspace;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.adapter.WorkspaceAdapter;
import com.example.btl_nhom20.api.ApiWeather;
import com.example.btl_nhom20.model.user.User;
import com.example.btl_nhom20.model.user.UserWorkspace;
import com.example.btl_nhom20.model.user.Workspace;
import com.example.btl_nhom20.model.weather.OpenWeatherMap;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// implements LocationListener
public class HomeFragment extends Fragment {
    private ReponsityWorkspace reponsitoryWorkspace;
    private ReponsityUserWorkspace reponsitoryUserWorkspace;
    private ReponsityUser reponsitoryUser;
    private ReponsityCalendar reponsitoryCalendar;
    private TextView userName,email;
    private CardView createWorkspace;
    private RecyclerView rcv_workspace;
    private WorkspaceAdapter mWorkspaceAdapter;
    private List<Workspace> mListWorkspace;
    private ImageView icon_notification;
    ProgressBar progressBar;
    String userId;
//    private static double lat = 0, lon = 0;
//    private static String API_KEY = "5221ea410af0251ab18f8f9502b64c18";

//    LocationManager locationManager;
//    TextView weather_temp, weather_city,windSpeed,humidity,weatherMain,weatherNation;
//    ImageView imgWeather;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        reponsitoryWorkspace = new ReponsityWorkspace(this.getContext());
        reponsitoryUser = new ReponsityUser(this.getContext());
        reponsitoryCalendar = new ReponsityCalendar(this.getContext());
        reponsitoryUserWorkspace = new ReponsityUserWorkspace(this.getContext());
        userId = MySharedPreferences.getInstance(getContext()).getString("User_Id");

        userName = view.findViewById(R.id.fragment_home_userName);
        email = view.findViewById(R.id.fragment_home_email);
        createWorkspace = view.findViewById(R.id.CreateWorkspace);
        rcv_workspace = view.findViewById(R.id.rcv_workspace);
        icon_notification = view.findViewById(R.id.call_notification);
        progressBar = view.findViewById(R.id.progressBar);

//        imgWeather = view.findViewById(R.id.weather_icon);
//        weather_city = view.findViewById(R.id.weather_city);
//        weather_temp = view.findViewById(R.id.weather_temp);
//        weatherMain  = view.findViewById(R.id.weather_main);
//        weatherNation = view.findViewById(R.id.weather_nation);
//        windSpeed = view.findViewById(R.id.windSpeed);
//        humidity = view.findViewById(R.id.humidity);

//        getLocation();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                CallAPiWeather(view);
//            }
//        },1000);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_workspace.setLayoutManager(linearLayoutManager);

        mListWorkspace = new ArrayList<>();
        mWorkspaceAdapter = new WorkspaceAdapter(mListWorkspace,getContext());
        rcv_workspace.setAdapter(mWorkspaceAdapter);
        getListWorkspace();

        User user = reponsitoryUser.getById(Integer.parseInt(userId));
        userName.setText(user.getUsername());
        email.setText(user.getEmail());

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);


       createWorkspace.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity().getApplication(), Create_workspace.class);
               startActivity(intent);
           }
       });
        icon_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void getListWorkspace(){
        List<UserWorkspace> tmp = reponsitoryUserWorkspace.getByUserId(Integer.parseInt(userId));
        if(tmp!=null){
            for (UserWorkspace model:tmp){
                mListWorkspace.add(reponsitoryWorkspace.getById(model.getWorkspaceId()));
            }
        }
        mWorkspaceAdapter.notifyDataSetChanged();
//        List<Integer> ressult = reponsitoryCalendar.getDistinctWorkspace(Integer.parseInt(userId));
//        for (int i = 0; i < ressult.size(); i++){
//            mListWorkspace.add(reponsitoryWorkspace.getById(ressult.get(i)));
//        }

    }
//    @SuppressLint("MissingPermission")
//    private void getLocation() {
//
//        try {
//            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,5, HomeFragment.this);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onLocationChanged(Location location) {
//        lat = location.getLatitude();
//        lon = location.getLongitude();
//    }


//    private  void CallAPiWeather(View view){
//        ApiWeather.apiWeather.openWeatherMap(lat,lon,API_KEY).enqueue(new Callback<OpenWeatherMap>() {
//            @Override
//            public void onResponse(retrofit2.Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
//                OpenWeatherMap openWeatherMap = response.body();
//
//                if(openWeatherMap != null){
//                    Picasso.with(getActivity()).load("https://openweathermap.org/img/wn/"+openWeatherMap.
//                            getWeather().get(0).getIcon()+"@2x.png").into(imgWeather);
//
//                    weather_city.setText(openWeatherMap.getName());
//                    weather_temp.setText(String.format("%.0f°C",(openWeatherMap.getMain().getTemp()- 273.15)));
//                    weatherMain.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
//                    humidity.setText(String.format("%s",openWeatherMap.getMain().getHumidity())+"%");
//                    windSpeed.setText(String.format("%.0f km/h",(openWeatherMap.getWind().getSpeed()*1.852)));
//                    weatherNation.setText(String.format("%s",openWeatherMap.getSys().getCountry()));
//                }
//            }
//            @Override
//            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {
//                Toast.makeText(getContext(),"call api fail",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}