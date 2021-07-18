package com.example.TVK.Model;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Controller.INotificationController;
import com.example.TVK.Model.User.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notification {
    private int id;
    private String title;
    private String content;
    private String idUser;

    String baseUrl = "http://192.168.1.5/androidwebservce/";

    public Notification() {
    }

    public Notification(int id, String title, String content, String idUser) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void showAllNotification(Context context, INotificationController iNotificationController)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                response -> {
                    JSONArray array = null;
                    ArrayList<Notification> notificationArrayList = new ArrayList<>();
                    try {
                        array = new JSONArray(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i=0 ; i< array.length(); i++)
                    {
                        try {
                            JSONObject object = array.getJSONObject(i);
                            notificationArrayList.add(new Notification(
                                    object.getInt("id"),
                                    object.getString("title"),
                                    object.getString("content"),
                                    object.getString("iduser")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    iNotificationController.onResponseListNotification(notificationArrayList);
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iNotificationController.showToast(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","getAllNotification");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}
