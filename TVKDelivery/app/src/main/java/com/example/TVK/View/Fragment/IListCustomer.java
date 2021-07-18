package com.example.TVK.View.Fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface IListCustomer {
    void setAdaper(View view);
    void changeFragment(Fragment fragment);
    void notifyerror(String message);



}
