package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.ICustomer;
import com.example.TVK.Model.User.IUser;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.MD5;
import com.example.TVK.Ultis.ValidateString;
import com.example.TVK.View.CustomerAdapter;
import com.example.TVK.View.Fragment.IAddNewCustomer;
import com.example.TVK.View.Fragment.IListCustomer;
import com.example.TVK.View.IMainAdminActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class ManageCustomerController implements IManageCustomerController, CallBack {
    IListCustomer iListCustomer;
    ICustomer customer = new Customer();
    IMainAdminActivity IadminActivity;
    IUser iUser;
    String name, phone, email;
    IAddNewCustomer iAddNewCustomer;
    boolean check;


    public boolean isCheck() {
        return check;
    }

    @Override
    public void updateStateCustomer(Context context,String phone, String state) {
        customer.updateStateCustomer(context,phone,state);
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public ManageCustomerController() {
    }

    public ManageCustomerController(IListCustomer iListCustomer) {
        this.iListCustomer = iListCustomer;
    }

    public ManageCustomerController(IAddNewCustomer iAddNewCustomer) {
        this.iAddNewCustomer = iAddNewCustomer;
    }

    @Override
    public CustomerAdapter loadadapter(Context context) {
        CustomerAdapter CustomerAdapter;
        ArrayList<Customer> customerArrayList;
        customerArrayList = new ArrayList<>();
        CustomerAdapter = new CustomerAdapter(context, R.layout.layout_custom_user, customerArrayList, new CustomerAdapter.IClickItemListener() {
            @Override
            public void onClickItemCustomer(Customer customer) {
                IadminActivity = (IMainAdminActivity) context;
                IadminActivity.gotoDetailFragment(customer);
            }
        });
        customer.getAllDataCustomer(context, customerArrayList, CustomerAdapter);
        return CustomerAdapter;
    }


    @Override
    public void OnCheckData(EditText txtcusname_add, EditText txtcusphone_add, EditText txtcusemail_add, Context context) {
        name = txtcusname_add.getText().toString().trim();
        phone = txtcusphone_add.getText().toString().trim();
        email = txtcusemail_add.getText().toString().trim();
        IadminActivity = (IMainAdminActivity) context;

        //name empty
        if(TextUtils.isEmpty(name))
        {
            txtcusname_add.requestFocus();
            txtcusname_add.setError("Không được để trống phần này");
            return;
        }

        //email  empty
        if(TextUtils.isEmpty(email))
        {
            txtcusemail_add.requestFocus();
            txtcusemail_add.setError("Không được để trống phần này");
            return;
        }
        //phone emty
        if(TextUtils.isEmpty(phone))
        {
            txtcusphone_add.requestFocus();
            txtcusphone_add.setError("Không được để trống phần này");
            return;
        }
        else
        {
            //phone match available format
            if(!ValidateString.isValid(phone,"phone"))
            {
                txtcusphone_add.requestFocus();
                txtcusphone_add.setError("Định dạng số điện thoại là +84 xxxx, nhập phần xxxx");
                return;
            }
        }
        if(!ValidateString.isValid(email,"email"))
        {
            txtcusemail_add.requestFocus();
            txtcusemail_add.setError("Vui lòng nhập dạng địa chỉ email");
            return;
        }
        CallBack callBack = new ManageCustomerController();
        customer.checkExistPhone(context, callBack,txtcusname_add, txtcusphone_add,txtcusemail_add);
    }

    @Override
    public void OnAddNewCustomer(String txtcusname_add, String txtcusphone_add, String txtcusemail_add, Context context) {
        Random random = new Random();
        String Username = "";
        String temp = convertToString(txtcusname_add);
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
        String Phone = txtcusphone_add;
        Customer new_customer = new Customer
                .CustomerBuilder().setUserName(Username)
                .setFullName(txtcusname_add)
                .setPassWord(MD5.HashMD5(Password))
                .setPhone(Phone)
                .setStatus("consudung")
                .setTypeOfUser("CUSTOMER")
                .build();
        new_customer.addNewCustomter(new_customer,context);
        IadminActivity = (IMainAdminActivity) context;
        //IadminActivity.sendSms("+84"+txtcusphone_add,"You username and password to login TVKDelivery is:"+ Username +","+ Password);
    }



    public static String convertToString(String value)
    {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {
        if (string_response.equals("Exist Phone"))
        {


        }else if(string_response.equals("No Exist Phone"))
        {
            Customer customer = (Customer) object;
            OnAddNewCustomer(customer.getFullName(),customer.getPhone(),customer.getEmail(),context);

        }
    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
}
