package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.View.DriverAdapter;

public interface IManageDriverController {
    DriverAdapter loadadapter(Context context);
    void OnCheckData(EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber,EditText txtaddress, EditText txtlicense, Context context);
    void OnAddNewDriver(String txtname, String txtphone, String txtgender, String txtemail, String txtidnumber, String txtaddress, String txtlicense,Context context);
    boolean isCheck();
    void updateStateDriver(Context context, String phone, String state);
}
