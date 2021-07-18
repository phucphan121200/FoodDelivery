package com.example.TVK.Model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Controller.ICreateEditOrderCustomerController;
import com.example.TVK.Controller.IOrderMByStatusController;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.GlobalUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements IOrder {
    private int idOrder;
    private int idUser;
    private String pickupAddress;
    private String deliveryAddress;
    private double mass;
    private String receiverName;
    private String receiverPhone;
    private String description;
    private double postage;
    private double total;

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private String state;
    Date startTime;
    Date endTime;
    int idDriver;
    private static Order instance;
    CallBack callBack;
    String baseUrl = "http://192.168.1.5/androidwebservce/";

    public static Order getInstance() {
        if(instance==null)
        {
            instance = new Order();
        }
        return instance;
    }

    public Order() {
    }

    public double getPostage() {
        return postage;
    }

    public double getTotal() {
        return total;
    }

    public static void setInstance(Order instance) {
        Order.instance = instance;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPostage(int postage) {
        this.postage = postage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }



    public int getIdOrder() {
        return idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public double getMass() {
        return mass;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getDescription() {
        return description;
    }


    public String getState() {
        return state;
    }


    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public Order(int id, int idUser, String pickupaddress, String deliveryaddress, double mass, String receiveName, String receiverphone, String description, double postage, double total, String state, Date starttime, Date endtime, int idDriver){}

    public Order(int idOrder, int idUser, String pickupAddress, String deliveryAddress, double mass, String receiverName, String receiverPhone, String decscription, int postage , int total, String state, Date startTime,  Date endTime, int idDriver)
    {
        this.idOrder=idOrder;
        this.idUser=idUser;
        this.pickupAddress=pickupAddress;
        this.deliveryAddress=deliveryAddress;
        this.mass=mass;
        this.receiverName=receiverName;
        this.receiverPhone=receiverPhone;
        this.description=decscription;
        this.postage=postage;
        this.total=total;
        this.state=state;
        this.startTime=startTime;
        this.endTime=endTime;
        this.idDriver=idDriver;
    }

    public void createOrder(Context context, Order order, ICreateEditOrderCustomerController iCreateEditOrderCustomerController)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iCreateEditOrderCustomerController.showToast(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCreateEditOrderCustomerController.showToast(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "createOrder");
                params.put("iduser", String.valueOf(order.getIdUser()));
                params.put("pickupaddress", order.getPickupAddress());
                params.put("deliveryaddress", order.getDeliveryAddress());
                params.put("mass", String.valueOf((int)order.getMass()));
                params.put("receivername", order.getReceiverName());
                params.put("receiverphone", order.getReceiverPhone());
                params.put("description", order.getDescription());
                params.put("postage", String.valueOf((int)order.getPostage()));
                params.put("total", String.valueOf((int)order.getTotal()));
                params.put("state", order.getState());
                params.put("startTime", order.getStartTime().toString());
                return params;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }

    public void cancelOrder(Context context, int idOrder, IOrderMByStatusController iOrderMByStatusController)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iOrderMByStatusController.responseCancel(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iOrderMByStatusController.responseCancel(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "cancelOrder");
                params.put("idorder", String.valueOf(idOrder));
                return params;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }

    public void getOrderByStatus(Context context, String status, IOrderMByStatusController iOrderMByStatusController) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        List<Order> orderArrayList = new ArrayList<Order>();
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("Error"))
                        {
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
                                    String start = object.getString("StartTime");
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date starttime = sdf.parse(start);
                                    String end = object.getString("EndTime");
                                    if (end=="null")
                                    {
                                        if(object.getString("IDDriver")!= "null") {
                                            Order current_order = new Order();
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setIdUser(object.getInt("IDUser"));
                                            current_order.setPickupAddress(object.getString("PickupAddress"));
                                            current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                            current_order.setMass(object.getDouble("Mass"));
                                            current_order.setReceiverName( object.getString("ReceiverName"));
                                            current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                            current_order.setDescription(object.getString("Description"));
                                            current_order.setPostage(object.getDouble("Postage"));
                                            current_order.setTotal(object.getDouble("Total"));
                                            current_order.setState(object.getString("State"));
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setStartTime(starttime);
                                            current_order.setEndTime(null);
                                            current_order.setIdDriver(object.getInt("IDDriver"));
                                            orderArrayList.add(current_order);
                                        }else {
                                            Order current_order = new Order();
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setIdUser(object.getInt("IDUser"));
                                            current_order.setPickupAddress(object.getString("PickupAddress"));
                                            current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                            current_order.setMass(object.getDouble("Mass"));
                                            current_order.setReceiverName( object.getString("ReceiverName"));
                                            current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                            current_order.setDescription(object.getString("Description"));
                                            current_order.setPostage(object.getDouble("Postage"));
                                            current_order.setTotal(object.getDouble("Total"));
                                            current_order.setState(object.getString("State"));
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setStartTime(starttime);
                                            current_order.setEndTime(null);
                                            current_order.setIdDriver(0);
                                            orderArrayList.add(current_order);
                                        }

                                    }else if(object.getString("IDDriver")=="null")
                                    {
                                        Date endtime= sdf.parse(end);
                                        Order current_order = new Order();
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setIdUser(object.getInt("IDUser"));
                                        current_order.setPickupAddress(object.getString("PickupAddress"));
                                        current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                        current_order.setMass(object.getDouble("Mass"));
                                        current_order.setReceiverName( object.getString("ReceiverName"));
                                        current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                        current_order.setDescription(object.getString("Description"));
                                        current_order.setPostage(object.getDouble("Postage"));
                                        current_order.setTotal(object.getDouble("Total"));
                                        current_order.setState(object.getString("State"));
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setStartTime(starttime);
                                        current_order.setEndTime(endtime);
                                        current_order.setIdDriver(0);
                                        orderArrayList.add(current_order);

                                    }else {
                                        Date endtime= sdf.parse(end);
                                        Order current_order = new Order();
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setIdUser(object.getInt("IDUser"));
                                        current_order.setPickupAddress(object.getString("PickupAddress"));
                                        current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                        current_order.setMass(object.getDouble("Mass"));
                                        current_order.setReceiverName( object.getString("ReceiverName"));
                                        current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                        current_order.setDescription(object.getString("Description"));
                                        current_order.setPostage(object.getDouble("Postage"));
                                        current_order.setTotal(object.getDouble("Total"));
                                        current_order.setState(object.getString("State"));
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setStartTime(starttime);
                                        current_order.setEndTime(endtime);
                                        current_order.setIdDriver(object.getInt("IDDriver"));
                                        orderArrayList.add(current_order);
                                    }
                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                    Log.i("loi",e.toString());
                                }
                            }
                        }
                        iOrderMByStatusController.setListOrder(orderArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iOrderMByStatusController.showToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","getOrderByStatus");
                params.put("status",status);
                params.put("iduser", String.valueOf(GlobalUser.getInstanceCustomer("CUSTOMER").getIdUser()));
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}
