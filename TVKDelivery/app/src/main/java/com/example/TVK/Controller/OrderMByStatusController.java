package com.example.TVK.Controller;

import android.view.View;

import com.example.TVK.Model.Order;
import com.example.TVK.View.Fragment.ICreateEditOrderCustomer;
import com.example.TVK.View.Fragment.IOrderManagementByStatus;
import com.example.TVK.View.Fragment.IOrderManagementCustomer;
import com.example.TVK.View.Fragment.OrderManagementCustomer;

import java.util.List;

public class OrderMByStatusController implements IOrderMByStatusController{

    IOrderManagementCustomer iOrderManagementCustomer;
    View view;

    public OrderMByStatusController(IOrderManagementCustomer iOrderManagementCustomer, View view) {
        this.iOrderManagementCustomer = iOrderManagementCustomer;
        this.view = view;
    }

    @Override
    public void getListOrderByStatus(String status) {
        Order.getInstance().getOrderByStatus(view.getContext(),status,this);
    }

    @Override
    public void setListOrder(List<Order> listOrder) {
        //iOrderManagementByStatus.setListOrder(view,listOrder);
        iOrderManagementCustomer.setListOrder(view,listOrder);
    }

    @Override
    public void showToast(String message) {
        iOrderManagementCustomer.showToast(message);
    }

    @Override
    public void cancelOrder(int idOrder) {
        Order.getInstance().cancelOrder(view.getContext(),idOrder,this);
    }

    @Override
    public void responseCancel(String message) {
        iOrderManagementCustomer.cancelOrder(message);
    }

}
