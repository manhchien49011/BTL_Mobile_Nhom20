package com.example.btl_nhom20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.R;
import com.example.btl_nhom20.model.user.User;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    List<User> users;
    private Context context;

    public EmployeeAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        User user = users.get(position);
        if(user == null){
            return;
        }
        holder.tv_name.setText(user.getUsername());
        holder.tv_email.setText(user.getEmail());

    }


    @Override
    public int getItemCount() {
        if(users != null){
            return users.size();
        }
        return 0;
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_email;
        private LinearLayout item_workspace;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_nameWorkspace);
            tv_email = itemView.findViewById(R.id.tv_emailWorkspace);
            item_workspace = itemView.findViewById(R.id.item_workspace);
        }
    }
}
