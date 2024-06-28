package com.example.btl_nhom20.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.R;
import com.example.btl_nhom20.WorkspaceActivityAdmin;
import com.example.btl_nhom20.model.user.Workspace;

import java.util.List;

public class WorkspaceAdapter extends RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder> {

    List<Workspace> workspaces;
    private Context context;

    public WorkspaceAdapter(List<Workspace> workspaces, Context context) {
        this.workspaces = workspaces;
        this.context = context;
    }



    @NonNull
    @Override
    public WorkspaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workspace_item,parent,false);
        return new WorkspaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkspaceViewHolder holder, int position) {
        Workspace workspace = workspaces.get(position);
        if(workspace == null){
            return;
        }
        holder.tv_name.setText(workspace.getNameWorkspace());
        holder.tv_email.setText(workspace.getEmail());
        holder.item_workspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToWorkspace(workspace);
            }
        });
    }

    private void onClickGoToWorkspace(Workspace workspace) {
        Intent intent = new Intent(context, WorkspaceActivityAdmin.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_workspace", workspace);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        if(workspaces != null){
            return workspaces.size();
        }
        return 0;
    }

    public class WorkspaceViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_email;
        private LinearLayout item_workspace;

        public WorkspaceViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_nameWorkspace);
            tv_email = itemView.findViewById(R.id.tv_emailWorkspace);
            item_workspace = itemView.findViewById(R.id.item_workspace);
        }
    }
}
