package com.example.TVK.View.Fragment;

import android.view.View;

import com.example.TVK.Model.Order;

import java.util.List;

public interface IOrderManagementCustomer {
    void setListOrder(View view, List<Order> listOrder);
    void showToast(String message);
    void detailOrder(int idOrder);
    void cancelOrder(String message);
    void loadRecyclerViewByStatus(String status);
}
