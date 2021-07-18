package com.example.TVK.View;

import com.example.TVK.Ultis.IViewUltis;

import org.json.JSONException;
import org.json.JSONObject;

public interface ILoginView extends IViewUltis {
    void rememberMe(boolean isRemembeMe, String type_of_user, JSONObject json_user) throws JSONException;
    void moveToMaiActivity(String type_of_user,JSONObject jsonObject);
}
