package com.example.TVK.Model;

import android.content.Context;

import com.example.TVK.View.ContactAdapter;

import java.util.ArrayList;

public interface IContact {
    void getAllDataContact(Context context, ArrayList<Contact> customerArrayList, ContactAdapter adapter);

}
