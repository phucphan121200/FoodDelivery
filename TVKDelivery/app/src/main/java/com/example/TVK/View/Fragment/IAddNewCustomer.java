package com.example.TVK.View.Fragment;

import android.widget.EditText;

import com.example.TVK.Ultis.IViewUltis;

public interface IAddNewCustomer extends IViewUltis {
    void toastinfor(String message);
    EditText getEdittext(String nameedittext);
    void sendSms(String toPhoneNumber, String message);




}
