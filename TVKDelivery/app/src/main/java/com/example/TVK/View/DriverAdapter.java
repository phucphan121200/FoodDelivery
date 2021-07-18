package com.example.TVK.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.TVK.Model.User.Driver;
import com.example.TVK.R;

import java.util.List;

public class DriverAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Driver> driverList;
    private IClickItemListener iClickItemListener;


    @Override
    public int getCount() {
        return driverList.size();
    }
    public interface IClickItemListener
    {
        void onClickItemDriver(Driver driver);
    }




    public DriverAdapter(Context context, int layout, List<Driver> driverList, IClickItemListener iClickItemListener) {
        this.context = context;
        this.layout = layout;
        this.driverList = driverList;
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
        DriverAdapter.ViewHolder holder;
        if(convertView ==null)
        {
            holder = new DriverAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtname = (TextView) convertView.findViewById(R.id.txtIDOrder);
            holder.txtphone = (TextView) convertView.findViewById(R.id.tvStarttime);
            holder.imageView= (ImageView) convertView.findViewById(R.id.txtpicture);
            holder.btndetailcus = (ImageView) convertView.findViewById(R.id.btnDetailOrderCus);
            convertView.setTag(holder);
        }
        else {
            holder = (DriverAdapter.ViewHolder) convertView.getTag();
        }
        Driver driver = driverList.get(position);
        holder.txtname.setText("Name: "+ driver.getFullName());
        holder.txtphone.setText("Phone: " + driver.getPhone());

        holder.btndetailcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemDriver(driver);
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txtname, txtphone;
        ImageView imageView, btndetailcus;
    }
}
