package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.TVK.Model.User.Driver;
import com.example.TVK.Model.User.IDriver;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.MD5;
import com.example.TVK.Ultis.ValidateString;
import com.example.TVK.View.DriverAdapter;
import com.example.TVK.View.Fragment.IAddNewDriver;
import com.example.TVK.View.Fragment.IListDriver;
import com.example.TVK.View.IMainAdminActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import static com.example.TVK.Controller.ManageCustomerController.convertToString;

public class ManageDriverController implements IManageDriverController, CallBack {
    IListDriver iListDriver;
    IDriver iDriver =new Driver();
    IMainAdminActivity iMainAdminActivity;
    String name, phone, email, gender, idnumber, license,address;
    boolean check;
    IAddNewDriver iAddNewDriver;
    static Boolean ExistPhone =false ,ExistID=false,ExistLicense=false;


    public ManageDriverController(IListDriver iListDriver) {
        this.iListDriver = iListDriver;
    }

    public ManageDriverController() {

    }

    public ManageDriverController(IAddNewDriver iAddNewDriver) {
        this.iAddNewDriver = iAddNewDriver;
    }

    @Override
    public DriverAdapter loadadapter(Context context) {

        DriverAdapter driverAdapter;
        ArrayList<Driver> driverArrayList;
        driverArrayList = new ArrayList<>();
        driverAdapter = new DriverAdapter(context, R.layout.layout_custom_driver, driverArrayList, new DriverAdapter.IClickItemListener() {
            @Override
            public void onClickItemDriver(Driver driver) {
                iMainAdminActivity = (IMainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(driver);
            }
        });
        iDriver.getAllDataDriver(context, driverArrayList, driverAdapter);
        return driverAdapter;
    }

    @Override
    public void OnCheckData(EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtaddress, EditText txtlicense, Context context) {
        name = txtname.getText().toString().trim();
        phone =txtphone.getText().toString().trim();
        gender = txtgender.getText().toString().trim();
        email = txtemail.getText().toString().trim();
        idnumber = txtidnumber.getText().toString().trim();
        license = txtlicense.getText().toString().trim();
        address = txtaddress.getText().toString().trim();
        if(TextUtils.isEmpty(name))
        {
            txtname.requestFocus();
            txtname.setError("Field cannot be left blank");
            return;
        }
        if(TextUtils.isEmpty(gender))
        {
            txtgender.requestFocus();
            txtgender.setError("Field cannot be left blank");
            return;
        }
        /*if(gender.equalsIgnoreCase("Male")== false || gender.equalsIgnoreCase("Female")== false)
        {
            txtgender.requestFocus();
            txtgender.setError("Field is not correct. Please fill Male or Female");
            return;
        }*/
        if(TextUtils.isEmpty(idnumber))
        {
            txtidnumber.requestFocus();
            txtidnumber.setError("Field cannot be left blank");
            return;
        }
        if(TextUtils.isEmpty(license))
        {
            txtlicense.requestFocus();
            txtlicense.setError("Field cannot be left blank");
            return;
        }
        if(TextUtils.isEmpty(address))
        {
            txtaddress.requestFocus();
            txtaddress.setError("Field cannot be left blank");
            return;
        }
        if(TextUtils.isEmpty(email))
        {
            txtemail.requestFocus();
            txtemail.setError("Field cannot be left blank");
            return;
        }

        if(TextUtils.isEmpty(phone))
        {
            txtphone.requestFocus();
            txtphone.setError("Field cannot be left blank");
            return;
        }
        else
        {
            //phone match available format
            if(!ValidateString.isValid(phone,"phone"))
            {
                txtphone.requestFocus();
                txtphone.setError("Format of phone is +84 xxxx, fill in xxxx");
                return;
            }
        }
        if(!ValidateString.isValid(email,"email"))
        {
            txtemail.requestFocus();
            txtemail.setError("Field cannot be format");
            return;
        }
        CallBack callBack = new ManageDriverController();
        iDriver.checkExistID(context,callBack,txtname,txtphone,txtgender,txtemail,txtidnumber,txtlicense,txtaddress);
        iDriver.checkExistLicense(context,callBack,txtname,txtphone,txtgender,txtemail,txtidnumber,txtlicense,txtaddress);
        iDriver.checkExistPhone(context,callBack,txtname,txtphone,txtgender,txtemail,txtidnumber,txtlicense,txtaddress);

    }

    @Override
    public void OnAddNewDriver(String txtname, String txtphone, String txtgender, String txtemail, String txtidnumber, String txtaddress, String txtlicense,Context context) {
        Random random = new Random();
        String Username = "";
        String temp = convertToString(txtname);
        for(int i = temp.length() - 1; i>-1 ;i--)
        {
            if (temp.codePointAt(i) == 32 )
            {
                Username = temp.substring(i+1)+random.nextInt(200);
                Username = Username.toLowerCase();
                break;
            }
        }
        String Password = "123456789";
        String Phone = txtphone;
        Driver new_driver = new Driver.DriverBuilder()
                .setUserName(Username)
                .setFullName(txtname)
                .setPassWord(MD5.HashMD5(Password))
                .setPhone(Phone)
                .setAddress(txtaddress)
                .setGender(txtgender)
                .setEmail(txtemail)
                .setIdnumber(txtidnumber)
                .setLicense(txtlicense)
                .setStatus("consudung")
                .setTypeOfUser("DRIVER")
                .build();
        new_driver.addNewDriver(new_driver,context);



    }

    @Override
    public boolean isCheck() {
        return check;
    }

    @Override
    public void updateStateDriver(Context context, String state, String phone) {
            iDriver.updateStateDriver(context,state,phone);
    }


    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {
        if(string_response.equals("Exist Idnumber"))
        {
            ExistID = false;
        }else if(string_response.equals("No Exist Idnumber"))
        {
            ExistID = true;
        }

        if (string_response.equals("Exist Phone"))
        {
            ExistPhone = false;
        }else if(string_response.equals("No Exist Phone"))
        {
            ExistPhone = true;
        }

        if(string_response.equals("No Exist License"))
        {
            ExistLicense = true;
        }
        else if(string_response.equals("Exist License"))
        {
            ExistLicense = false;
        }



        if(ExistPhone && ExistLicense && ExistID ) {
            Driver driver = (Driver) object;
            OnAddNewDriver(driver.getFullName(), driver.getPhone(), driver.getGender(), driver.getEmail(), driver.getIdnumber(), driver.getAddress(), driver.getGplx(), context);
        }
    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
}
