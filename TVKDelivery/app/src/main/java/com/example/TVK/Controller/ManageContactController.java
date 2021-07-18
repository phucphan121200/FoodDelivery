package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.Model.Contact;
import com.example.TVK.Model.IContact;
import com.example.TVK.R;
import com.example.TVK.View.ContactAdapter;
import com.example.TVK.View.Fragment.IListContact;
import com.example.TVK.View.IMainAdminActivity;

import java.util.ArrayList;

public class ManageContactController implements  IManageContactController{
    IListContact iListContact;
    IContact iContact = new Contact();
    IMainAdminActivity iMainAdminActivity;

    public ManageContactController(IListContact iListContact) {
        this.iListContact = iListContact;
    }

    public ManageContactController() {
    }

    @Override
    public ContactAdapter loadadapter(Context context) {
        ContactAdapter contactAdapter;
        ArrayList<Contact> contactArrayList;
        contactArrayList = new ArrayList<>();
        contactAdapter = new ContactAdapter(context, R.layout.layout_custom_contact, contactArrayList, new ContactAdapter.IClickItemListener() {
            @Override
            public void onClickItemContact(Contact contact) {
                //iMainAdminActivity = (IMainAdminActivity) context;
                //iMainAdminActivity.gotoDetailFragment(contact);
            }
        });
        iContact.getAllDataContact(context, contactArrayList, contactAdapter);
        return contactAdapter;
    }
}
