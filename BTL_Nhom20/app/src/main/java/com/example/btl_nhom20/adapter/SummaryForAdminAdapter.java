package com.example.btl_nhom20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.R;
import com.example.btl_nhom20.model.user.MonthlySummary;

import java.util.HashMap;
import java.util.List;

public class SummaryForAdminAdapter extends RecyclerView.Adapter<SummaryForAdminAdapter.SummaryEmploymentViewHolder>{
    private Context context;
    HashMap<Integer, List<MonthlySummary>> hashMap;

    public SummaryForAdminAdapter(Context context, HashMap<Integer, List<MonthlySummary>> hashMap) {
        this.context = context;
        this.hashMap = hashMap;
    }

    @NonNull
    @Override
    public SummaryEmploymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_for_admin_item,parent,false);
        return new SummaryEmploymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryEmploymentViewHolder holder, int position) {
        int key = position + 1;
        holder.textViewMonthSummaryAdmin.setText("Tháng " + key);

        holder.itemUserByMonth.setLayoutManager(new LinearLayoutManager(context));
        holder.itemUserByMonth.setAdapter(new SummaryUserByMonthAdapter(context,hashMap.get(key)));
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    public class SummaryEmploymentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMonthSummaryAdmin;
        RecyclerView itemUserByMonth;

        public SummaryEmploymentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMonthSummaryAdmin = itemView.findViewById(R.id.textViewMonthSummaryAdmin);
            itemUserByMonth                                 = itemView.findViewById(R.id.itemUserByMonthAdmin);
        }
    }
}
