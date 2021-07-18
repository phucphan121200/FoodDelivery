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
import com.example.TVK.View.ContactAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact implements IContact {
    private int id;
    private int iduser;
    private String title;
    private String message;

    public Contact() {
    }

    String baseUrl = "http://192.168.1.5/androidwebservce/";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Contact(int id, int iduser, String title, String message) {
        this.id = id;
        this.iduser = iduser;
        this.title = title;
        this.message = message;
    }

    @Override
    public void getAllDataContact(Context context, ArrayList<Contact> contactArrayList, ContactAdapter adapter) {
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
                                contactArrayList.add(new Contact(
                                        object.getInt("ID"),
                                        object.getInt("IDUser"),
                                        object.getString("Title"),
                                        object.getString("Message")
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
                params.put("type","getDataContact");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}
