package com.example.TVK.Model.User;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Controller.IEditInforCustomerController;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.View.CustomerAdapter;
import com.example.TVK.View.Fragment.IEditCustomerInfor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements ICustomer , Serializable {
    private static Customer instance;
    private IGetAPICallback iGetAPICallback;
    CallBack callBack;

    public Customer(String name, String phone, String email)
    {
        super(name,phone,email);
    }

    public Customer(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser)
    {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser);
    }

    public Customer(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser, int isReceiveNotification) {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser, isReceiveNotification);
    }

    public Customer() {

    }

    @Override
    public void setIsReceiveNotification(int isReceiveNotification) {
        super.setIsReceiveNotification(isReceiveNotification);
    }

    @Override
    public void setIdUser(int idUser) {
        super.setIdUser(idUser);
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public void setPassWord(String passWord) {
        super.setPassWord(passWord);
    }

    @Override
    public void setActivationCode(String activationCode) {
        super.setActivationCode(activationCode);
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public void setResetPassword(String resetPassword) {
        super.setResetPassword(resetPassword);
    }

    @Override
    public void setTypeOfUser(String typeOfUser) {
        super.setTypeOfUser(typeOfUser);
    }

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }
    public void addNewCustomter(Customer customer, Context context, IViewUltis iViewUltis, IGetAPICallback iGetAPICallback_argument) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        iGetAPICallback = iGetAPICallback_argument;
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, super.baseUrl + "getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray jsonArray = new JSONArray(response);
                            iGetAPICallback.onGetDataSucess(null, null, response, iViewUltis, "addNewCustomer");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iGetAPICallback.onResponseError(error.getMessage(), iViewUltis);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "register");
                params.put("username", customer.getUserName().trim());
                params.put("password", customer.getPassWord().trim());
                params.put("phone", customer.getPhone().trim());
                params.put("activationcode", customer.getActivationCode().trim());
                params.put("state", customer.getStatus().trim());
                params.put("typeofuser", customer.getTypeOfUser().trim());
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }


   public void addNewCustomter(Customer customer, Context context ){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, super.baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Registration Successfully"))
                        {
                            Toast.makeText(context, "Registration Successfully",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","newaccountcustomer");
                params.put("username",customer.getUserName().trim());
                params.put("name",customer.getFullName().trim());
                params.put("password",customer.getPassWord().trim());
                params.put("phone",customer.getPhone().trim());
                params.put("state",customer.getStatus().trim());
                params.put("typeofuser",customer.getTypeOfUser().trim());
                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void checkExistPhone(Context context, CallBack callBack_ar,EditText txtcusname_add, EditText txtcusphone_add, EditText txtcusemail_add) {
        callBack = callBack_ar;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Exist Phone"))
                {
                    txtcusphone_add.requestFocus();
                    txtcusphone_add.setError("Number phone is exist");
                    callBack.onGetDataSucess(null,null,response,context,null);

                }else if (response.equals("No Exist Phone")){
                    //txtcusphone_add.requestFocus();
                    //txtcusphone_add.setError("Number phone is not exist");
                    Customer customer = new Customer(txtcusname_add.getText().toString().trim(),txtcusphone_add.getText().toString().trim(),txtcusemail_add.getText().toString().trim());
                    callBack.onGetDataSucess(null,null,response,context,customer);
                    txtcusemail_add.setText("");
                    txtcusname_add.setText("");
                    txtcusphone_add.setText("");
                    txtcusemail_add.setCursorVisible(false);
                    txtcusname_add.setCursorVisible(false);
                    txtcusphone_add.setCursorVisible(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onResponseError(error.getMessage(), null);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","checkExist");
                params.put("typecheck","phone");
                params.put("phone",txtcusphone_add.getText().toString().trim());
                return params;
            }

        };
        requestQueue.add(stringRequest);

    }

    public void updateStateCustomer(Context context, String state, String phone)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Successful"))
                {
                    Toast.makeText(context,"Successful",Toast.LENGTH_LONG).show();

                }else if(response.equals("Something went wrong"))
                {
                    Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","editStateUser");
                params.put("phone",phone);
                params.put("state",state);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public void getAllDataCustomer(Context context, ArrayList<Customer> customerArrayList, CustomerAdapter adapter) {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONArray array = null;
                            try {
                                array = new JSONArray(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (int i=0 ; i< array.length(); i++)
                            {
                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    customerArrayList.add(new Customer(
                                            object.getInt("ID"),
                                            object.getString("Name"),
                                            object.getString("Gender"),
                                            object.getString("Phone"),
                                            object.getString("Address"),
                                            object.getString("Email"),
                                            object.getString("Username"),
                                            object.getString("Password"),
                                            object.getString("ActivationCode"),
                                            object.getString("State"),
                                            object.getString("ResetPasswordCode"),
                                            object.getString("TypeUser")
                                    ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("type","getDataCustomer");
                    return params;
                }
            };
            requestQueue.add(jsonArrayRequest);
    }

    public void updateCustomer(Context context, IEditInforCustomerController iEditInforCustomerController, Customer customer)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iEditInforCustomerController.showToast(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iEditInforCustomerController.showToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","editUser");
                params.put("name",customer.getFullName());
                params.put("gender",customer.getGender());
                params.put("phone",customer.getPhone());
                params.put("address",customer.getAddress());
                params.put("email",customer.getEmail());
                params.put("isReceiveNotification", String.valueOf(customer.getIsReceiveNotification()));
                params.put("id", String.valueOf(customer.getIdUser()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public static class CustomerBuilder {
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


        public CustomerBuilder setIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public CustomerBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public CustomerBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public CustomerBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public CustomerBuilder setPassWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public CustomerBuilder setActivationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public CustomerBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public CustomerBuilder setResetPassword(String resetPassword) {
            this.resetPassword = resetPassword;
            return this;
        }

        public CustomerBuilder setTypeOfUser(String typeOfUser) {
            this.typeOfUser = typeOfUser;
            return this;
        }

        public Customer build() {
            return new Customer(
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
