package com.example.btl_nhom20.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_nhom20.WorkspaceActivityAdmin;
import com.example.btl_nhom20.fragment.AddWorkspaceFragment;
import com.example.btl_nhom20.fragment.HomeWorkspaceFragment;
import com.example.btl_nhom20.fragment.ProjectWorkspaceFragment;
import com.example.btl_nhom20.fragment.WorkplaceFragment;

public class ViewPager2AdapterWorkspace extends FragmentStateAdapter {
    public ViewPager2AdapterWorkspace(@NonNull WorkspaceActivityAdmin fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeWorkspaceFragment();
            case 1:
                return  new ProjectWorkspaceFragment();
            case 2:
                return new WorkplaceFragment();
            case 3:
                return new AddWorkspaceFragment();
            default:
                return new HomeWorkspaceFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
