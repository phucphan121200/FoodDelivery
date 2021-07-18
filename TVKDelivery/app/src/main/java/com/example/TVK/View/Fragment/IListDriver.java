package com.example.TVK.View.Fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface IListDriver {
    void setAdaper(View view);
    void changeFragment(Fragment fragment);
}
