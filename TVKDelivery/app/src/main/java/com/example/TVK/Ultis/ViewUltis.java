package com.example.TVK.Ultis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TVK.R;

public class ViewUltis extends AppCompatActivity implements IViewUltis {

    private static ViewUltis instance;

    public ViewUltis()
    {

    }
    public static ViewUltis getInstance() {
        if (instance == null) {
            instance = new ViewUltis();
        }
        return instance;
    }

    public void hideKeyboard1(View view, boolean hasFocus) {
        if(!hasFocus)
        {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
    public void showToast(String str, Context context)
    {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void open_dialogLoading(int gravity) {

    }

    @Override
    public void close_dialogLoading() {

    }

    @Override
    public void hideKeyboard(View view, boolean hasFocus) {

    }

    @Override
    public void showToast(String str_error) {

    }

    @Override
    public EditText getEditText(String name_editText) {
        return null;
    }



}
