package com.example.TVK.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.TVK.Model.Contact;
import com.example.TVK.R;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Contact> contactList;
    private ContactAdapter.IClickItemListener iClickItemListener;

    public ContactAdapter(Context context, int layout, List<Contact> contactList, ContactAdapter.IClickItemListener iClickItemListener) {
        this.context = context;
        this.layout = layout;
        this.contactList = contactList;
        this.iClickItemListener = iClickItemListener;
    }

    public interface IClickItemListener
    {
        void onClickItemContact(Contact contact);
    }
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactAdapter.ViewHolder holder;
        if(convertView ==null)
        {
            holder = new ContactAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txttitle = (TextView) convertView.findViewById(R.id.txttitle);
            holder.txtmessage = (TextView) convertView.findViewById(R.id.txtmessage);
            holder.txtid= (TextView) convertView.findViewById(R.id.txtuser_contact);
            convertView.setTag(holder);
        }
        else {
            holder = (ContactAdapter.ViewHolder) convertView.getTag();
        }
        Contact contact = contactList.get(position);
        holder.txttitle.setText("Title: "+contact.getTitle());
        holder.txtmessage.setText("Message: " +contact.getMessage());
        holder.txtid.setText("ID User: "+contact.getIduser());

        holder.txtid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemContact(contact);
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txttitle, txtmessage, txtid;
    }

}
