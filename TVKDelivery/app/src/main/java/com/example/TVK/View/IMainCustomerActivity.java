package com.example.TVK.View;

import androidx.fragment.app.Fragment;

import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;

public interface IMainCustomerActivity {
    void getView();
    void loadFragment(Fragment fragment);
    void gotoDetailFragment(Customer customer);
    void sendSms(String toPhoneNumber, String message);
    void gotoDetailFragment(Driver driver);
    void changeFragment(int count);
}
