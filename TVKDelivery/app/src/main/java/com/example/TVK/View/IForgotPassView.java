package com.example.TVK.View;

import com.example.TVK.Ultis.IViewUltis;

public interface IForgotPassView extends IViewUltis {
    void open_dialog(int gravity, String phone,String otp_code);
    void combackLoginActivity();
    void sendOTPMessage(String phone_number);
}
