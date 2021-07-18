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
import com.example.TVK.View.FeedbackAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Feedback implements IFeedback{
    private int id;
    private int idOrder;
    private float rating;
    private String note;
    String baseUrl = "http://192.168.1.5/androidwebservce/";

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Feedback(int id, int idOrder, float rating, String note) {
        this.id = id;
        this.idOrder = idOrder;
        this.rating = rating;
        this.note = note;
    }

    @Override
    public void getAllDataFeedback(Context context, ArrayList<Feedback> feedbackArrayList, FeedbackAdapter adapter) {
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
                                feedbackArrayList.add(new Feedback(
                                        object.getInt("ID"),
                                        object.getInt("IDOrder"),
                                        object.getInt("Rating"),
                                        object.getString("Note"))
                                );
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
                params.put("type","getDataFeedback");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

}
