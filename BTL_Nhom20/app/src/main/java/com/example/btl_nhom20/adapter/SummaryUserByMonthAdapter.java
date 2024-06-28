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

public class SummaryUserByMonthAdapter extends RecyclerView.Adapter<SummaryUserByMonthAdapter.Viewholeder> {

    Context context;
    List<MonthlySummary> monthlySummaries;
    public SummaryUserByMonthAdapter(Context context, List<MonthlySummary> monthlySummaries){
        this.context = context;
        this.monthlySummaries = monthlySummaries;
    }

    @NonNull
    @Override
    public Viewholeder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_by_month, parent, false);
        return new Viewholeder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholeder holder, int position) {
        MonthlySummary model = monthlySummaries.get(position);
        holder.textViewUsernameSummaryAdmin.setText(model.getUsername());
        holder.lateForWorkForAdmin.setText(model.getNumLateForWork()+"");
        holder.workOnTimeForAdmin.setText(model.getNumWorkOnTime()+"");
        holder.offWorkForAdmin.setText(model.getNumOffWork()+"");
    }

    @Override
    public int getItemCount() {
        return monthlySummaries.size();
    }

    public class Viewholeder extends RecyclerView.ViewHolder {

        TextView textViewUsernameSummaryAdmin,
                lateForWorkForAdmin,
                workOnTimeForAdmin,
                offWorkForAdmin;
        public Viewholeder(@NonNull View itemView) {
            super(itemView);
            textViewUsernameSummaryAdmin = itemView.findViewById(R.id.textViewUsernameSummaryAdmin);
            lateForWorkForAdmin = itemView.findViewById(R.id.lateForWorkForAdmin);
            workOnTimeForAdmin = itemView.findViewById(R.id.workOnTimeForAdmin);
            offWorkForAdmin = itemView.findViewById(R.id.offWorkForAdmin);
        }
    }


}
