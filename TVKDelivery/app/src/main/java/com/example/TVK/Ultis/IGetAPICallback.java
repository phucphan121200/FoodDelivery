package com.example.TVK.Ultis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface IGetAPICallback {
    void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, IViewUltis iViewUltis,String typeOfRequest) throws JSONException;
    void onResponseError(String error, IViewUltis iViewUltis);
    void onGetDataError(String error_message);
}
