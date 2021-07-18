package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.Model.Notification;
import com.example.TVK.View.Fragment.INotification;

import java.util.ArrayList;

public class NotificationController implements INotificationController {

    private INotification iNotification;
    private Context context;
    public NotificationController(INotification iNotification, Context context) {
        this.iNotification = iNotification;
        this.context = context;
    }

    @Override
    public void showAllNotification() {
        Notification notification = new Notification();
        notification.showAllNotification(context,this);
    }

    @Override
    public void onResponseListNotification(ArrayList<Notification> notificationArrayList) {
        iNotification.onResponseListNotification(notificationArrayList);
    }

    @Override
    public void showToast(String message) {
        iNotification.showToast(message);
    }
}
