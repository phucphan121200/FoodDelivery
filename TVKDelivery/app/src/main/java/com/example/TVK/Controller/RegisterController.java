package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;

import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.IUser;
import com.example.TVK.Model.User.UserFactory;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.Ultis.MD5;
import com.example.TVK.Ultis.ValidateString;
import com.example.TVK.View.IRegisterView;
import com.example.TVK.View.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterController implements IRegisterController, IGetAPICallback {

    IRegisterView iRegisterView;
    IUser iUser;
    String userName, passWord, phone, otp_code, confirmPassword;
    private boolean isCheck;
    public RegisterController(RegisterActivity registerView) {
        this.iRegisterView = registerView;
    }

    public RegisterController() {

    }

    @Override
    public void OnCheckDataAndSendOTP(EditText txtUsername, EditText txtPassword, EditText txtConfirmPassword, EditText txtPhone, Context context) {
        userName = txtUsername.getText().toString().trim();
        passWord = txtPassword.getText().toString().trim();
        confirmPassword = txtConfirmPassword.getText().toString().trim();
        phone = txtPhone.getText().toString().trim();

        //userName empty
        if(TextUtils.isEmpty(userName))
        {
            txtUsername.requestFocus();
            txtUsername.setError("Không được để trống phần này");
            return;
        }
        else
        {
            //userName match available format
            if(!ValidateString.isValid(userName,"username"))
            {
                txtUsername.requestFocus();
                txtUsername.setError("Tên đăng nhập không được có khoảng trắng và kí tự đặc biệt và từ 8-16 kí tự");
                return;
            }
        }

        //passWord empty
        if(TextUtils.isEmpty(passWord))
        {
            txtPassword.requestFocus();
            txtPassword.setError("Không được để trống phần này");
            return;
        }
        else
        {
            //passWord match available format
            if(!ValidateString.isValid(passWord,"password"))
            {
                txtPassword.requestFocus();
                txtPassword.setError("Mật khẩu không được có khoảng trắng và kí tự đặc biệt và từ 8-16 kí tự");
                return;
            }
        }

        //confirm password empty
        if(TextUtils.isEmpty(confirmPassword))
        {
            txtConfirmPassword.requestFocus();
            txtConfirmPassword.setError("Không được để trống phần này");
            return;
        }
        else
        {
            //Confirm passWord not matched with password
            if(!confirmPassword.equals(passWord))
            {
                txtConfirmPassword.requestFocus();
                txtConfirmPassword.setError("Xác nhận mật khẩu phải khớp với mật khẩu");
                return;
            }
        }

        //phone emty
        if(TextUtils.isEmpty(phone))
        {
            txtPhone.requestFocus();
            txtPhone.setError("Không được để trống phần này");
            return;
        }
        else
        {
            //phone match available format
            if(!ValidateString.isValid(phone,"phone"))
            {
                txtPhone.requestFocus();
                txtPhone.setError("Định dạng số điện thoại là +84 xxxx, nhập phần xxxx");
                return;
            }
        }
        iUser = UserFactory.getUser("CUSTOMER");
        IViewUltis iViewUltis = this.iRegisterView;
        IGetAPICallback iGetAPICallback = new RegisterController();
        iUser.getAllDataUser(context,iViewUltis,iGetAPICallback);
        //bật dialogOTPLoading lên
        iRegisterView.open_dialogLoading(Gravity.CENTER);
    }

    @Override
    public void OnAddNewCustomer(EditText txtUsername, EditText txtPassword, EditText txtPhone,String otp,Context context) {
        Customer new_customer = new Customer
                .CustomerBuilder().setUserName(txtUsername.getText().toString().trim())
                .setPassWord(MD5.HashMD5(txtPassword.getText().toString().trim()))
                .setPhone(txtPhone.getText().toString().trim())
                .setActivationCode(otp)
                .setStatus("consudung")
                .setTypeOfUser("CUSTOMER")
                .build();

        IViewUltis iViewUltis = this.iRegisterView;

        IGetAPICallback iGetAPICallback = new RegisterController();

        new_customer.addNewCustomter(new_customer,context,iViewUltis,iGetAPICallback);
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, IViewUltis iViewUltis,String typeOfRequest) throws JSONException {
        iRegisterView = (IRegisterView) iViewUltis;
        if(typeOfRequest.equals("CheckData"))
        {
            ArrayList<Customer> lst_customer = new ArrayList<>();

            boolean isExist=false;
            for (int i=0 ; i< jsonArray.length(); i++)
            {
                try {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if(object.getString("Username").equals(iRegisterView.getEditText("Username").getText().toString().trim()))
                    {
                        iRegisterView.getEditText("Username").requestFocus();
                        iRegisterView.getEditText("Username").setError("Tên đăng nhập đã tồn tại");
                        isExist = true;
                    }
                    if(object.getString("Phone").equals(iRegisterView.getEditText("Phone").getText().toString().trim()))
                    {
                        iRegisterView.getEditText("Phone").requestFocus();
                        iRegisterView.getEditText("Phone").setError("Số điện thoại đã được sử dụng");
                        isExist = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(isExist)
            {
                iRegisterView.close_dialogLoading();
                return;
            }

            iRegisterView.sendOTPMessage(iRegisterView.getEditText("Phone").getText().toString().trim());

        }
        else if(typeOfRequest.equals("addNewCustomer"))
        {
            //cập nhật mật khẩu
            //ILoginView iLoginView = (ILoginView) iViewUltis;
            //ILoginView iLoginView = (ILoginView) new LoginController();
            if(string_response.equals("Registration Successfully"))
            {
                //thành công
                iRegisterView.showToast("Đăng ký thành công");
                //iLoginView.setMessage("Đăng ký thành công");
            }
            else
            {
                iRegisterView.showToast("Đăng ký thất bại thất bại");
                //iLoginView.setMessage("Đăng ký thành công thất bại");
            }
            iRegisterView.combackLoginActivity();
        }

    }

    @Override
    public void onResponseError(String error, IViewUltis iViewUltis) {
        iRegisterView = (IRegisterView) iViewUltis;
        iRegisterView.showToast(error);
        iRegisterView.close_dialogLoading();
    }

    @Override
    public void onGetDataError(String error_message) {

    }
}
