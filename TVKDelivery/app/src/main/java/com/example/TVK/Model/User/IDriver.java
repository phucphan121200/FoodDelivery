package com.example.TVK.Model.User;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.DriverAdapter;

import java.util.ArrayList;

public interface IDriver {
    void getAllDataDriver(Context context, ArrayList<Driver> driverArrayList, DriverAdapter adapter);
    void addNewDriver(Driver driver, Context context);
    void checkExistPhone(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);
    void updateStateDriver(Context context, String state, String id);
    void checkExistLicense(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);
    void checkExistID(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);

}
