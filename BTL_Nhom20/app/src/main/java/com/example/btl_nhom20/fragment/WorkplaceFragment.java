package com.example.btl_nhom20.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.btl_nhom20.R;


public class WorkplaceFragment extends Fragment {





    public WorkplaceFragment() {
        // Required empty public constructor
    }

    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workplace_wsp, container, false);

        return view;


    }
}