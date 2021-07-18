package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;

import com.example.TVK.Model.User.IUser;
import com.example.TVK.Model.User.UserFactory;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.MD5;
import com.example.TVK.View.ILoginView;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.View.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginController implements ILoginController, IGetAPICallback {

    ILoginView loginView;
    IUser iUser;

    public static final String KEY_USER_TO_MAIN = "KEY_USER_TO_MAIN";

    public LoginController(LoginActivity loginView) {
        this.loginView = loginView;
    }

    public LoginController() {

    }

    @Override
    public void OnLogin(EditText etdangnhap, EditText etmatkhau, Context context) {
        IViewUltis iViewUltis = loginView;
        String userName = etdangnhap.getText().toString().trim();
        String passWord = etmatkhau.getText().toString().trim();
        //userName empty
        if(TextUtils.isEmpty(userName))
        {
            etdangnhap.requestFocus();
            etdangnhap.setError("Không được để trống phần này");
            return;
        }
        //passWord empty
        if(TextUtils.isEmpty(passWord))
        {
            etmatkhau.requestFocus();
            etmatkhau.setError("Không được để trống phần này");
            return;
        }
        iUser = UserFactory.getUser("USER");
        IGetAPICallback iGetAPICallback = new LoginController();
        iUser.checkLogin(userName, MD5.HashMD5(passWord),context, iViewUltis,iGetAPICallback);
        //bật dialogOTPLoading lên
        loginView.open_dialogLoading(Gravity.CENTER);
    }


    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, IViewUltis iViewUltis,String typeOfRequest) throws JSONException {
        loginView = (ILoginView) iViewUltis;
        if(jsonObject==null)
        {
            loginView.getEditText("Username").requestFocus();
            loginView.getEditText("Username").setError("Tài khoản hoặc mật khẩu không đúng");
            loginView.close_dialogLoading();
        }
        else
        {
            String typeuser = jsonObject.getString("TypeUser");
            loginView = (ILoginView) iViewUltis;
            loginView.moveToMaiActivity(typeuser,jsonObject);
        }

    }

    @Override
    public void onResponseError(String error, IViewUltis iViewUltis) {
        loginView = (ILoginView) iViewUltis;
        loginView.showToast(error);
        loginView.close_dialogLoading();
    }

    @Override
    public void onGetDataError(String error_message) {

    }


}
