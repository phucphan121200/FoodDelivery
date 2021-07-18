package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

public interface IForgotPassController {
    void OnCheckPhoneAndSendOTP(EditText phone, Context context);
    void ChangePassword(EditText phone, EditText new_paswrod,String otp,EditText confirm_password, Context context);
}
