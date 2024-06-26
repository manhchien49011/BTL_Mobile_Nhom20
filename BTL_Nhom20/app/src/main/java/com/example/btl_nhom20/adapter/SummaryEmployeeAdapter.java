package com.example.btl_nhom20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.R;
import com.example.btl_nhom20.model.user.MonthlySummary;

import java.util.List;

public class SummaryEmployeeAdapter extends RecyclerView.Adapter<SummaryEmployeeAdapter.SummaryEmploymentViewHolder>{
    Context context;
    List<MonthlySummary> monthlySummaryList;

    public SummaryEmployeeAdapter(Context context, List<MonthlySummary> monthlySummaryList) {
        this.context = context;
        this.monthlySummaryList = monthlySummaryList;
    }

    @NonNull
    @Override
    public SummaryEmploymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_employee_item,parent,false);
        return new SummaryEmploymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryEmploymentViewHolder holder, int position) {
        MonthlySummary monthlySummary = monthlySummaryList.get(position);
        String month  = "Tháng : "+monthlySummary.getMonth();
        holder.tv_month.setText(month);
        holder.tv_workOnTime.setText(String.valueOf(monthlySummary.getNumWorkOnTime()));
        holder.tv_lateForTime.setText(String.valueOf(monthlySummary.getNumLateForWork()));
        holder.tv_offWork.setText(String.valueOf(monthlySummary.getNumOffWork()));
    }

    @Override
    public int getItemCount() {
        if(monthlySummaryList != null){
            return monthlySummaryList.size();
        }
        return 0;
    }

    public class SummaryEmploymentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_month,tv_workOnTime,tv_lateForTime,tv_offWork;
            public SummaryEmploymentViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_month = itemView.findViewById(R.id.tv_month);
                tv_workOnTime = itemView.findViewById(R.id.workOnTime);
                tv_lateForTime = itemView.findViewById(R.id.lateForWork);
                tv_offWork = itemView.findViewById(R.id.tv_offWork);
            }
        }
}
