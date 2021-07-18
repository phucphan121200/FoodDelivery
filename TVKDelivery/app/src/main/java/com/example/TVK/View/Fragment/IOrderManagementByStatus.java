package com.example.TVK.View.Fragment;

import android.view.View;

import com.example.TVK.Model.Order;

import java.util.List;

public interface IOrderManagementByStatus {
    void setListOrder(View v, List<Order> listOrder);
    void showToast(String message);
}
