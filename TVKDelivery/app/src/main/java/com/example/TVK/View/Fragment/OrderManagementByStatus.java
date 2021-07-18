package com.example.TVK.View.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.Controller.OrderMByStatusController;
import com.example.TVK.Model.Order;
import com.example.TVK.R;

import java.util.List;

public class OrderManagementByStatus extends Fragment implements IOrderManagementByStatus{
    View view;
    private int indexFragment=-1;
    ProgressDialog progressDialog;
    TextView textView;
    RecyclerView recyclerView;
    OrderManagementCustomerAdapter orderManagementCustomerAdapter;

    OrderMByStatusController orderMByStatusController;

    public OrderManagementByStatus(int index) {
        this.indexFragment =index;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_recyclerview, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");
        progressDialog.show();
       // orderMByStatusController = new OrderMByStatusController(this,view);

        recyclerView = view.findViewById(R.id.rvListOrder1);

        switch (indexFragment)
        {
            case 0:
                //processing
                orderMByStatusController.getListOrderByStatus("PROCESSING");
                break;
            case 1:
                orderMByStatusController.getListOrderByStatus("DELIVERING");
                break;
            case 2:
                orderMByStatusController.getListOrderByStatus("DELIVERED");
                break;
            case 3:
                orderMByStatusController.getListOrderByStatus("CANCELED");
                break;
        }
    }

    @Override
    public void setListOrder(View v,List<Order> listOrder) {
        /*if(indexFragment==0)
        {
            orderManagementCustomerAdapter=new OrderManagementCustomerAdapter(true,v, listOrder,recyclerView,orderMByStatusController,getActivity(),this);
        }
        else
        {
            orderManagementCustomerAdapter=new OrderManagementCustomerAdapter(false,v, listOrder,recyclerView,orderMByStatusController,getActivity());
        }
        recyclerView.setAdapter(orderManagementCustomerAdapter);
        orderManagementCustomerAdapter.notifyDataSetChanged();
        progressDialog.dismiss();*/
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
