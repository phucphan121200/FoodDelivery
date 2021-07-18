package com.example.TVK.Controller;

import com.example.TVK.Model.Order;

public interface ICreateEditOrderCustomerController {
    void createOrder(Order order);
    void showToast(String message);
}
