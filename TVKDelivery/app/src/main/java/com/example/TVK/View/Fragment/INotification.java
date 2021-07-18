package com.example.TVK.View.Fragment;

import com.example.TVK.Model.Notification;

import java.util.ArrayList;

public interface INotification {
    void onResponseListNotification(ArrayList<Notification> notificationArrayList);
    void showToast(String message);
}
