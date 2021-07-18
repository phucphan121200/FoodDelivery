package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

public interface IRegisterController  {
    void OnCheckDataAndSendOTP(EditText txtUsername, EditText txtPassword, EditText txtConfirmPassword, EditText txtPhone, Context context);
    void OnAddNewCustomer(EditText txtUsername, EditText txtPassword, EditText txtPhone,String otp,Context context);

}
