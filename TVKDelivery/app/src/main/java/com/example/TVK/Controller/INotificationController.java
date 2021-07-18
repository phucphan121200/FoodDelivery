package com.example.TVK.Controller;

import com.example.TVK.Model.Notification;

import java.util.ArrayList;

public interface INotificationController {
    void showAllNotification();
    void onResponseListNotification(ArrayList<Notification> notificationArrayList);
    void showToast(String message);
}
