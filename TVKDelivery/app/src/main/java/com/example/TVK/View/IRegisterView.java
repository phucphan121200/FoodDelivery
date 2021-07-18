package com.example.TVK.View;

import com.example.TVK.Ultis.IViewUltis;

public interface IRegisterView extends IViewUltis {
    void open_dialog(int gravity);
    void combackLoginActivity();
    public void sendOTPMessage(String phone_number);
}
