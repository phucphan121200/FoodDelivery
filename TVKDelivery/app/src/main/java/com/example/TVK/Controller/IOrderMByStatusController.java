package com.example.TVK.Controller;

import com.example.TVK.Model.Order;

import java.util.List;

public interface  IOrderMByStatusController {
    void getListOrderByStatus(String status);
    void setListOrder(List<Order> listOrder);
    void showToast(String message);
    void cancelOrder(int idOrder);
    void responseCancel(String message);
}
