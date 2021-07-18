package com.example.TVK.Ultis;

import android.view.View;
import android.widget.EditText;

public interface IViewUltis {
    void open_dialogLoading(int gravity);
    void close_dialogLoading();
    void hideKeyboard(View view, boolean hasFocus);
    void showToast(String str_error);
    EditText getEditText(String name_editText);
}
