package com.example.TVK.Ultis;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

public interface CallBack {
    void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object);
    void onResponseError(String error, Context context);
    void onGetDataError(String error_message, Context context);
}
