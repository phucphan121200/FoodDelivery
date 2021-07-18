package com.example.TVK.View.Fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder{

    TextView tvTitle, tvContent;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.tvContent);
        tvTitle = itemView.findViewById(R.id.tvTitle);
    }
}
