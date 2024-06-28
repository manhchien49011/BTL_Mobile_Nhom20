package com.example.btl_nhom20.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_nhom20.fragment.AccountFragment;
import com.example.btl_nhom20.fragment.HomeFragment;
import com.example.btl_nhom20.fragment.ProjectFragment;
import com.example.btl_nhom20.fragment.WorkspaceFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return  new ProjectFragment();
            case 2:
                return new WorkspaceFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
