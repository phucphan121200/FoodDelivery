package com.example.TVK.Viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.R;

public class OrderMByStatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView tvIDOrder, tvStartTime, tvTotal, tvDeliveryAdd;
    public ImageButton btn;

    ItemClickListener itemClickListener;

    public OrderMByStatusViewHolder(@NonNull View itemView) {
        super(itemView);

        tvIDOrder = itemView.findViewById(R.id.txtIDOrder);
        tvStartTime = itemView.findViewById(R.id.tvStarttime);
        tvTotal = itemView.findViewById(R.id.tvTotalItem);
        tvDeliveryAdd = itemView.findViewById(R.id.tvDeliverAdd);
        btn = itemView.findViewById(R.id.btnDetailOrderCus);

        itemView.setOnClickListener(this);

        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
