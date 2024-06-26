package com.example.btl_nhom20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.R;
import com.example.btl_nhom20.Reponsity.ReponsitoryNotification;
import com.example.btl_nhom20.Reponsity.ReponsityCalendar;
import com.example.btl_nhom20.Reponsity.ReponsityUserWorkspace;
import com.example.btl_nhom20.SharedPreferences.MySharedPreferences;
import com.example.btl_nhom20.model.user.ModelCalendar;
import com.example.btl_nhom20.model.user.Notification;
import com.example.btl_nhom20.model.user.UserWorkspace;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {


    List<Notification> notifications;
    ReponsityCalendar reponsityCalendar;
    ReponsitoryNotification reponsitoryNotification;
    ReponsityUserWorkspace reponsityUserWorkspace;



    private Context context;

    public NotificationAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
        reponsityCalendar = new ReponsityCalendar(context);
        reponsitoryNotification = new ReponsitoryNotification(context);
        reponsityUserWorkspace = new ReponsityUserWorkspace(context);
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
            Notification notification = notifications.get(position);
            if(notification == null){
                return;
            }
            if(notification.getIsAccept() == 1){
                holder.tv_emailSender.setText(notification.getEmailSender());
                holder.tv_message.setText(notification.getMessage());
                holder.btnAccept.setVisibility(View.GONE);
                holder.btnRefure.setVisibility(View.GONE);
                holder.tv_accept.setVisibility(View.VISIBLE);
                holder.item_notification.setBackground(ContextCompat.getDrawable(context,R.color.notificationWatched));
            }
           else if(notification.getIsRefuse() == 1){
                holder.tv_emailSender.setText(notification.getEmailSender());
                holder.tv_message.setText(notification.getMessage());
                holder.btnAccept.setVisibility(View.GONE);
                holder.btnRefure.setVisibility(View.GONE);
                holder.tv_refuse.setVisibility(View.VISIBLE);
                holder.item_notification.setBackground(ContextCompat.getDrawable(context,R.color.notificationWatched));
            }
           else{
                holder.tv_emailSender.setText(notification.getEmailSender());
                holder.tv_message.setText(notification.getMessage());
                holder.item_notification.setBackground(ContextCompat.getDrawable(context,R.color.notificationActive));
            }
            holder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference reference = database.getReference();
                    java.util.Calendar mCalendar;
                    int year;
                    int month;
                    int day;
                    mCalendar = java.util.Calendar.getInstance();
                    year = mCalendar.get(java.util.Calendar.YEAR);
                    month = mCalendar.get(java.util.Calendar.MONTH);
                    day = mCalendar.get(java.util.Calendar.DAY_OF_MONTH);
                    String date = day + "/"+(month+1) +"/"+year;

//                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String uid = MySharedPreferences.getInstance(context).getString("User_Id");
                    int idWsp = notification.getWorkspaceId();
                    ModelCalendar modelCalendar = new ModelCalendar();
                    modelCalendar.setUser_Id(Integer.parseInt(uid));
                    modelCalendar.setWorkspace_Id(idWsp);
                    modelCalendar.setDateOfEmployment(date);
                    modelCalendar.setYear(year);
                    modelCalendar.setMonth(month+1);
                    modelCalendar.setDay(day);
                    if(reponsityCalendar.insert(modelCalendar)){
                        reponsityUserWorkspace.insert(new UserWorkspace(Integer.parseInt(uid), idWsp, false));
                        Toast.makeText(context, "Thêm lịch công việc thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Thêm lịch công việc thất bại", Toast.LENGTH_SHORT).show();
                    }

                    notification.setIsAccept(1);
                    if(reponsitoryNotification.update(notification)){
                        Toast.makeText(context,"Bạn đã tham gia workspace",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"Có lỗi xảy ra với update noti_Adapter",Toast.LENGTH_SHORT).show();
                    }
//                    reference.child("Users").child(uid).child("Messages").child(String.valueOf(notification.getId())).child("isAccept").setValue(1);

                    holder.btnAccept.setVisibility(View.GONE);
                    holder.btnRefure.setVisibility(View.GONE);
                    holder.tv_accept.setVisibility(View.VISIBLE);

                }

            });
            holder.btnRefure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference reference = database.getReference();
                    String uid = MySharedPreferences.getInstance(context).getString("User_Id");
                    Toast.makeText(context,"Bạn đã từ chối tham gia workspace",Toast.LENGTH_SHORT).show();
                    notification.setIsRefuse(1);
                    if(reponsitoryNotification.update(notification)){
                        Toast.makeText(context,"Update noti refuse success",Toast.LENGTH_SHORT).show();
                    }
//                    reference.child("Users").child(uid).child("Messages").child(String.valueOf(notification.getId())).child("isRefuse").setValue(1);
                    holder.btnAccept.setVisibility(View.GONE);
                    holder.btnRefure.setVisibility(View.GONE);
                    holder.tv_refuse.setVisibility(View.VISIBLE);
                }
            });
    }
    @Override
    public int getItemCount() {
        if(notifications != null){
            return notifications.size();
        }
        return 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView tv_emailSender,tv_message,tv_accept,tv_refuse;
        LinearLayout btnAccept,btnRefure,item_notification;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_emailSender = itemView.findViewById(R.id.tv_emailSender);
            tv_message = itemView.findViewById(R.id.tv_message);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnRefure = itemView.findViewById(R.id.btnRefuse);
            item_notification = itemView.findViewById(R.id.item_notification);
            tv_accept = itemView.findViewById(R.id.tv_accept);
            tv_refuse = itemView.findViewById(R.id.tv_refuse);
        }
    }
}
