package com.example.TVK.View.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.Model.Notification;
import com.example.TVK.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    private View view;
    private ArrayList<com.example.TVK.Model.Notification> notificationArrayList;
    private RecyclerView recyclerView;
    private FragmentActivity fragmentActivity;
    private INotification iNotification;
    public NotificationAdapter(View view, ArrayList<Notification> notifications, RecyclerView recyclerView, FragmentActivity fragmentActivity,
                               INotification iNotification) {
        this.view = view;
        this.recyclerView = recyclerView;
        this.iNotification=iNotification;
        this.fragmentActivity = fragmentActivity;
        this.iNotification = iNotification;
        this.notificationArrayList = notifications;
    }
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_notifycation, parent, false);

        return new NotificationViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.tvTitle.setText(notificationArrayList.get(position).getTitle());
        holder.tvContent.setText(notificationArrayList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }
}
