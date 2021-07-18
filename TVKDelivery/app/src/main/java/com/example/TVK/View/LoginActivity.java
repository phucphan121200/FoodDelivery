package com.example.TVK.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TVK.Controller.ILoginController;
import com.example.TVK.Controller.LoginController;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.Ultis.IViewUltis;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements ILoginView {

    ILoginController loginController;
    IViewUltis iViewUltis;
    Context context;
    EditText etdangnhap, etmatkhau;
    Button btndangnhap;
    TextView tvdangki,tvQuenmatkhau;
    CheckBox cbGhiNhoDangNhap;
    Dialog dialog_API;
    SharedPreferences sharedPreferences;
    String message=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController = new LoginController(this);
        context = this;
        anhxa();
        checkSharedPreferences();
        btndangnhap.setOnClickListener(v -> {
            loginController.OnLogin(etdangnhap,etmatkhau,context);
        });
        tvdangki.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        tvQuenmatkhau.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
            startActivity(intent);
        });
        etdangnhap.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        etmatkhau.setOnFocusChangeListener((v,hasFocus) ->{
            hideKeyboard(v,hasFocus);
        });

    }
    private void anhxa()
    {
        etdangnhap = findViewById(R.id.etdangnhap);
        etmatkhau = findViewById(R.id.etmatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        tvdangki = findViewById(R.id.tvdangki);
        tvQuenmatkhau = findViewById(R.id.tvquenmatkhau);
        cbGhiNhoDangNhap = findViewById(R.id.cbGhiNhoDangNhap);
    }
    private void checkSharedPreferences()
    {
        sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);

        /*SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("logined_user",null);

        editor.apply();*/

        boolean isRememberme = sharedPreferences.getBoolean("rememberMe",false);

        String type_of_user = sharedPreferences.getString("type_of_user",null);

        JSONObject logined_user;

        String json_string = sharedPreferences.getString("logined_user",null);
        if(json_string==null)
        {
            logined_user = null;
        }
        else
        {
            Gson g = new Gson();
            logined_user = g.fromJson(json_string,JSONObject.class);
        }

        //rememberMe(false,null,"json_user");
        if(isRememberme)
        {
            //chuyển đến Main Mneu tương ứng
            if(type_of_user != null)
            {
                moveToMaiActivity(type_of_user,logined_user);
            }
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        etdangnhap.setText("");
        etdangnhap.clearFocus();
        etdangnhap.setError(null);
        etmatkhau.setText("");
        etdangnhap.clearFocus();
        etdangnhap.setError(null);
        if(message!=null)
        {
            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
        }
    }
    public void setMessage(String outside_message)
    {
        this.message = outside_message;
    }

    @Override
    protected void onPause() {
        super.onPause();
        message = null;
    }

    @Override
    public void hideKeyboard(View view,boolean hasFocus) {
        if(!hasFocus)
        {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        //ViewUltis.getInstance().hideKeyboard1(view,hasFocus);
    }

    @Override
    public void showToast(String str_error) {
        Toast.makeText(LoginActivity.this,str_error,Toast.LENGTH_LONG).show();
    }

    @Override
    public EditText getEditText(String name_editText) {
            switch (name_editText)
            {
                case "Username":
                    return etdangnhap;
                case "Password":
                    return etmatkhau;
                default:
                    return null;
            }
    }

    @Override
    public void rememberMe(boolean isRemembeMe, String type_of_user, JSONObject json_user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(isRemembeMe)
        {
            editor.putBoolean("rememberMe",true);
        }
        else
        {
            editor.putBoolean("rememberMe",false);
        }
        editor.putString("type_of_user",type_of_user);
        editor.putString("logined_user",json_user.toString());
        editor.apply();
    }

    @Override
    public void moveToMaiActivity(String type_of_user,JSONObject jsonObject) {
        //chuyển màn hình
        // create intent to show Main Activity
        if(type_of_user==null)
        {
            etdangnhap.requestFocus();
            etdangnhap.setError("Sai tài khoản hoặc mật khẩu");
            close_dialogLoading();
            return;
        }
        else
        {
            rememberMe(cbGhiNhoDangNhap.isChecked(),type_of_user,jsonObject);
            Intent intent;
            switch (type_of_user)
            {
                case "ADMIN":
                    GlobalUser.getInstanceAdmin(type_of_user);
                    try {
                        GlobalUser.setGlobalUser(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(context, MainAdminActivity.class);
                    break;
                case "DRIVER":
                    GlobalUser.getInstanceDriver(type_of_user);
                    try {
                        GlobalUser.setGlobalUser(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(context, MainDriverActivity.class);
                    break;
                case "CUSTOMER":
                    GlobalUser.getInstanceCustomer(type_of_user);
                    try {
                        GlobalUser.setGlobalUser(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(context, MainCustomerActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: ");//user.login(user));
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            // send data if need
            //intent.putExtra(KEY_USER_TO_MAIN, user.getIdUser());
            // start Main Activity
            context.startActivity(intent);
            finish();
        }

    }

    @Override
    public void open_dialogLoading(int gravity) {
        dialog_API = new Dialog(this);
        dialog_API.setContentView(R.layout.layout_dialog_loading);
        dialog_API.setCancelable(false);

        //dialogOTPLoading alignment
        Window window = dialog_API.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);
        dialog_API.show();
    }

    @Override
    public void close_dialogLoading() {
            dialog_API.dismiss();
    }





}