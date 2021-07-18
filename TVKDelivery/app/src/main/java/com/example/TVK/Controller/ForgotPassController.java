package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;

import com.example.TVK.Model.User.IUser;
import com.example.TVK.Model.User.UserFactory;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.Ultis.MD5;
import com.example.TVK.View.ForgotPassActivity;
import com.example.TVK.View.IForgotPassView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassController implements IForgotPassController, IGetAPICallback {
    IForgotPassView iForgotPassView;
    IUser iUser;
    String otp_code;
    public ForgotPassController (ForgotPassActivity forgotPassActivity)
    {
        this.iForgotPassView = forgotPassActivity;
    }

    public ForgotPassController() {

    }

    @Override
    public void OnCheckPhoneAndSendOTP(EditText phone, Context context) {
        String phone_number = phone.getText().toString().trim();
        if(TextUtils.isEmpty(phone_number))
        {
            phone.requestFocus();
            phone.setError("Không được để trống phần này");
            return;
        }
        iUser = UserFactory.getUser("CUSTOMER");
        IViewUltis iViewUltis = this.iForgotPassView;
        //chạy trước 1 lần
        IGetAPICallback iGetAPICallback = new ForgotPassController();
        iUser.getAllDataUser(context,iViewUltis,iGetAPICallback);
        //bật dialogOTPLoading lên
        iForgotPassView.open_dialogLoading(Gravity.CENTER);

    }

    @Override
    public void ChangePassword(EditText phone,EditText new_paswrod,String otp, EditText confirm_password, Context context) {

        String new_pass = new_paswrod.getText().toString().trim();
        String confirm_pass = confirm_password.getText().toString().trim();
        String phone_number = phone.getText().toString().trim();

        //new password emtpy
        if(TextUtils.isEmpty(new_pass))
        {
            new_paswrod.requestFocus();
            new_paswrod.setError("Không được để trống phần này");
            return;
        }
        //confirm password emtpy
        if(TextUtils.isEmpty(confirm_pass))
        {
            confirm_password.requestFocus();
            confirm_password.setError("Không được để trống phần này");
            return;
        }

        //new password matched cofirm password
        if(!new_pass.equals(confirm_pass))
        {
            confirm_password.requestFocus();
            confirm_password.setError("Xác nhận mật khẩu và mật khẩu mới phải trùng khớp");
            return;
        }
        new_pass = MD5.HashMD5(new_pass);
        iUser = UserFactory.getUser("CUSTOMER");
        IViewUltis iViewUltis = this.iForgotPassView;
        //chạy trước 1 lần
        IGetAPICallback iGetAPICallback = new ForgotPassController();
        iUser.changePassword(new_pass,phone_number,otp,context,iViewUltis,iGetAPICallback);
        //bật dialogOTPLoading lên
        iForgotPassView.open_dialogLoading(Gravity.CENTER);
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, IViewUltis iViewUltis, String typeOfRequest) throws JSONException {
        iForgotPassView = (IForgotPassView) iViewUltis;
        if(typeOfRequest.equals("CheckData"))
        {
            boolean isExist=false;
            for (int i=0 ; i< jsonArray.length(); i++)
            {
                try {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if(object.getString("Phone").equals(iForgotPassView.getEditText("Phone").getText().toString().trim()))
                    {
                        isExist = true;
                        break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(!isExist)
            {
                iForgotPassView.getEditText("Phone").requestFocus();
                iForgotPassView.getEditText("Phone").setError("Không tìm thấy tài khoản tương ứng");
                iForgotPassView.close_dialogLoading();
                return;
            }

            iForgotPassView.close_dialogLoading();
            iForgotPassView.sendOTPMessage(iForgotPassView.getEditText("Phone").getText().toString().trim());
        }
        else if(typeOfRequest.equals("ChangePassword"))
        {
            //cập nhật mật khẩu
            //ILoginView iLoginView = (ILoginView) new LoginController();
            if(string_response.equals("Change password Successfully"))
            {
                //thành công
                iForgotPassView.showToast("Đổi pass thành công");
                //iLoginView.setMessage("Đổi pass thành công");
            }
            else
            {
                iForgotPassView.showToast("Đổi pass thất bại");
                //iLoginView.setMessage("Đổi pass thất bại");
            }
            iForgotPassView.combackLoginActivity();
        }
    }

    @Override
    public void onResponseError(String error, IViewUltis iViewUltis) {
        iForgotPassView = (IForgotPassView) iViewUltis;
        iForgotPassView.showToast(error);
        iForgotPassView.close_dialogLoading();
    }

    @Override
    public void onGetDataError(String error_message) {

    }
}
