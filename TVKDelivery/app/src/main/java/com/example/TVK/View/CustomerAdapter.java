package com.example.TVK.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.TVK.Model.User.Customer;
import com.example.TVK.R;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Customer> customerList;
    private IClickItemListener iClickItemListener;


    @Override
    public int getCount() {
        return customerList.size();
    }

    public interface IClickItemListener
    {
            void onClickItemCustomer(Customer customer);
    }



    public CustomerAdapter(Context context, int layout, List<Customer> customerList, IClickItemListener iClickItemListener) {
        this.context = context;
        this.layout = layout;
        this.customerList = customerList;
        this.iClickItemListener = iClickItemListener;
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
        ViewHolder holder;
        if(convertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtname = (TextView) convertView.findViewById(R.id.txtIDOrder);
            holder.txtphone = (TextView) convertView.findViewById(R.id.tvStarttime);
            holder.imageView= (ImageView ) convertView.findViewById(R.id.txtpicture);
            holder.btndetailcus = (ImageView) convertView.findViewById(R.id.btnDetailOrderCus);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Customer customer = customerList.get(position);
        holder.txtname.setText("Name: "+customer.getFullName());
        holder.txtphone.setText("Phone: " +customer.getPhone());

        holder.btndetailcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemCustomer(customer);

            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txtname, txtphone;
        ImageView imageView, btndetailcus;
    }

}
