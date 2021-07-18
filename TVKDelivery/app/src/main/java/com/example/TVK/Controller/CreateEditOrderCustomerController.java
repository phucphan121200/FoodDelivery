package com.example.TVK.Controller;

import android.view.View;

import com.example.TVK.Model.Order;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.View.Fragment.ICreateEditOrderCustomer;
import com.example.TVK.View.Fragment.IEditCustomerInfor;

public class CreateEditOrderCustomerController implements ICreateEditOrderCustomerController {
    ICreateEditOrderCustomer iCreateEditOrderCustomer;
    View view;

    public CreateEditOrderCustomerController(ICreateEditOrderCustomer iCreateEditOrderCustomer, View view) {
        this.iCreateEditOrderCustomer = iCreateEditOrderCustomer;
        this.view = view;
    }

    @Override
    public void createOrder(Order order) {
        Order.getInstance().createOrder(view.getContext(),order,this);
    }

    @Override
    public void showToast(String message) {
        iCreateEditOrderCustomer.showToast(message);
    }
}
