package com.example.TVK.Controller;

import android.view.View;

import com.example.TVK.Model.User.Customer;
import com.example.TVK.View.Fragment.IEditCustomerInfor;

public class EditInforCustomerController implements IEditInforCustomerController {
    IEditCustomerInfor iEditCustomerInfor;
    View view;

    public EditInforCustomerController(IEditCustomerInfor iEditCustomerInfor, View view) {
        this.iEditCustomerInfor = iEditCustomerInfor;
        this.view = view;
    }

    @Override
    public void updateInfor(Customer customer) {
        Customer.getInstance().updateCustomer(view.getContext(),this,customer);
    }

    @Override
    public void showToast(String message) {
        iEditCustomerInfor.showToast(message);
    }
}
