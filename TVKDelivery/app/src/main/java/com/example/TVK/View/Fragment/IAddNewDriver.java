package com.example.TVK.View.Fragment;

import com.example.TVK.Ultis.IViewUltis;

public interface IAddNewDriver extends IViewUltis {
    void toastinfor(String message);
    void sendSms(String toPhoneNumber, String message);
}
