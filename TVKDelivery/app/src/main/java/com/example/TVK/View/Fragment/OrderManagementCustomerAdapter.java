package com.example.TVK.View.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.Controller.IOrderMByStatusController;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.Viewholder.OrderMByStatusViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderManagementCustomerAdapter extends RecyclerView.Adapter<OrderMByStatusViewHolder>  {
    View view;
    List<Order> orderList ;
    public RecyclerView recyclerView;
    private boolean isProcessing = false;
    ProgressDialog progressDialog;
    FragmentActivity fragmentActivity;
    IOrderMByStatusController iOrderMByStatusController;
    IOrderManagementCustomer iOrderManagementCustomer;
    Dialog dialog;
    TextView textView;
    Button ok,cancel;
    private int current_position=-1;


    public OrderManagementCustomerAdapter(boolean isProcessing,View view, List<Order> orderList, RecyclerView recyclerView,
                                          IOrderMByStatusController iOrderMByStatusController, FragmentActivity fragmentActivity,
                                          IOrderManagementCustomer iOrderManagementCustomer) {
        this.view = view;
        this.orderList = orderList;
        this.recyclerView = recyclerView;
        this.iOrderMByStatusController=iOrderMByStatusController;
        this.fragmentActivity = fragmentActivity;
        this.isProcessing = isProcessing;
        this.iOrderManagementCustomer = iOrderManagementCustomer;
    }


    @NonNull
    @Override
    public OrderMByStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_order_customer, parent, false);

        return new OrderMByStatusViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderMByStatusViewHolder holder, int position) {
        holder.tvIDOrder.setText(String.valueOf(orderList.get(position).getIdOrder()));
        holder.tvStartTime.setText(orderList.get(position).getStartTime().toString());
        holder.tvTotal.setText(String.valueOf(orderList.get(position).getTotal()));
        holder.tvDeliveryAdd.setText(orderList.get(position).getDeliveryAddress());

        holder.btn.setOnClickListener(v->{
            iOrderManagementCustomer.detailOrder(position);
        });
        holder.setItemClickListener((view1, position1, isLongClick) -> {
            if(isLongClick && isProcessing)
            {
                current_position = position;
                initDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void initDialog()
    {
        dialog = new Dialog(fragmentActivity);
        dialog.setContentView(R.layout.layout_dialog_confirm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations =R.style.animation;
        textView = dialog.findViewById(R.id.txtquestion);
        ok = dialog.findViewById(R.id.dialog_ok);
        cancel = dialog.findViewById(R.id.dialog_cancel);

        textView.setText("Are you sure to cancel this order?");
        ok.setOnClickListener(v -> {
            iOrderMByStatusController.cancelOrder(orderList.get(current_position).getIdOrder());
        });
        cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void dismissDialog(String message)
    {
        if(message.equals("Successful"))
        {
            //thành công
            iOrderManagementCustomer.loadRecyclerViewByStatus("DANGXULY");
        }
        dialog.dismiss();
        Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
    }


}
