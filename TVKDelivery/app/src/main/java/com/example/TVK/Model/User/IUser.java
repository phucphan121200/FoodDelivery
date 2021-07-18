package com.example.TVK.Model.User;

import android.content.Context;

import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;

public interface IUser {
    void checkLogin(String userName, String passWord, Context context, IViewUltis iViewUltis, IGetAPICallback iGetAPICallback);
    void getAllDataUser(Context context,IViewUltis iViewUltis,IGetAPICallback iGetAPICallback);
    void changePassword(String new_password, String phone, String resetPassword,Context context,IViewUltis iViewUltis,IGetAPICallback iGetAPICallback);
}
