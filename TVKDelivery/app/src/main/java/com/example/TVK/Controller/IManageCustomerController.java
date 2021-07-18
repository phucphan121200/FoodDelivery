package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.View.CustomerAdapter;

public interface IManageCustomerController {
    CustomerAdapter loadadapter(Context context);
    void OnCheckData(EditText txtcusname_add, EditText txtcusphone_add, EditText txtcusemail_add, Context context);
    void OnAddNewCustomer(String txtcusname_add, String txtcusphone_add, String txtcusemail_add,Context context);
    boolean isCheck();
    void updateStateCustomer(Context context, String phone, String state);


}
