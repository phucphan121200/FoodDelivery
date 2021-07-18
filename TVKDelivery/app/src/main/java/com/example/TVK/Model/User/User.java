package com.example.TVK.Model.User;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Model.IOrder;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User implements IUser{
    private int idUser;
    private String fullName;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String userName;
    private String passWord;
    private String activationCode;
    private String status;
    private String resetPassword;
    private String typeOfUser;
    private int isReceiveNotification;

    public User(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser, int isReceiveNotification) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.activationCode = activationCode;
        this.status = status;
        this.resetPassword = resetPassword;
        this.typeOfUser = typeOfUser;
        this.isReceiveNotification = isReceiveNotification;
    }

    public int getIsReceiveNotification() {
        return isReceiveNotification;
    }

    public void setIsReceiveNotification(int isReceiveNotification) {
        this.isReceiveNotification = isReceiveNotification;
    }

    private static User instance;

    private IGetAPICallback iGetAPICallback;
    private IOrder iOrder;

    String baseUrl = "http://192.168.1.5/androidwebservce/";

    public User(){

    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public String getStatus() {
        return status;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public IGetAPICallback getiGetAPICallback() {
        return iGetAPICallback;
    }

    public IOrder getiOrder() {
        return iOrder;
    }

    public User(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord,
                String activationCode, String status, String resetPassword, String typeOfUser) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.activationCode = activationCode;
        this.status = status;
        this.resetPassword = resetPassword;
        this.typeOfUser = typeOfUser;
    }

    public User(String fullName, String gender, String phone, String address, String email) {
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public User(String fullName, String phone, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    @Override
    public void checkLogin(String userName_login, String password_login, Context context, IViewUltis iViewUltis,IGetAPICallback iGetAPICallback_argument)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        iGetAPICallback = iGetAPICallback_argument;
        String url = baseUrl+ "getdata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response.equals("Error"))
                    {
                        iGetAPICallback.onGetDataSucess(null,null,response, iViewUltis,null);
                    }
                    else
                    {
                        JSONObject object = new JSONObject(response);
                        iGetAPICallback.onGetDataSucess(object,null,null, iViewUltis,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iGetAPICallback.onResponseError(error.getMessage(), iViewUltis);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","login");
                params.put("username",userName_login);
                params.put("password",password_login);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void getAllDataUser(Context context, IViewUltis iViewUltis,IGetAPICallback iGetAPICallback_argument) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        iGetAPICallback = iGetAPICallback_argument;
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            iGetAPICallback.onGetDataSucess(null,jsonArray,null,iViewUltis,"CheckData");
                        } catch (JSONException e) {
                               e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iGetAPICallback.onResponseError(error.getMessage(),iViewUltis);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","getData");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void changePassword(String new_password, String phone_number, String resetPassword,Context context, IViewUltis iViewUltis,IGetAPICallback iGetAPICallback_argument) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        iGetAPICallback = iGetAPICallback_argument;
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray jsonArray = new JSONArray(response);
                            iGetAPICallback.onGetDataSucess(null,null,response,iViewUltis,"ChangePassword");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iGetAPICallback.onResponseError(error.getMessage(),iViewUltis);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","forgotpassword");
                params.put("newpassword",new_password);
                params.put("phone",phone_number);
                params.put("resetpassword",resetPassword);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public static class UserBuilder{
        private int idUser;
        private String fullName;
        private String gender;
        private String phone;
        private String address;
        private String email;
        private String userName;
        private String passWord;
        private String activationCode;
        private String status;
        private String resetPassword;
        private String typeOfUser;

        public UserBuilder setIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public UserBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder setPassWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public UserBuilder setActivationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public UserBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public UserBuilder setResetPassword(String resetPassword) {
            this.resetPassword = resetPassword;
            return this;
        }

        public UserBuilder setTypeOfUser(String typeOfUser) {
            this.typeOfUser = typeOfUser;
            return this;
        }

        public User build() {
            return new User(
                            this.idUser,
                            this.fullName,
                            this.gender,
                            this.phone,
                            this.address,
                            this.email,
                            this.userName,
                            this.passWord,
                            this.activationCode,
                            this.status,
                            this.resetPassword,
                            this.typeOfUser);
        }
    }

}
