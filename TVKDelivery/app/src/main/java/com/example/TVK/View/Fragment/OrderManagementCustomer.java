package com.example.TVK.View.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TVK.Controller.IOrderMByStatusController;
import com.example.TVK.Controller.OrderMByStatusController;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.View.IMainCustomerActivity;

import java.util.List;

public class OrderManagementCustomer extends Fragment implements IOrderManagementCustomer {

    IMainCustomerActivity iMainCustomerActivity;
    ProgressDialog progressDialog;
    ImageButton imgbtnadddriver2;
    private int indexFragment=0;
    TextView textView;
    RecyclerView recyclerView;
    View view;
    Button btProcessing, btDelivering, btDelivered, btCancel;
    IOrderMByStatusController iOrderMByStatusController;
    OrderManagementCustomerAdapter orderManagementCustomerAdapter;
    List<Order> orderList;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public OrderManagementCustomer(IMainCustomerActivity iMainCustomerActivity,FragmentActivity fragmentActivity) {
        this.iMainCustomerActivity = iMainCustomerActivity;
        fragmentManager = fragmentActivity.getSupportFragmentManager();
        // Required empty public constructor
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_management_customer, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");

        recyclerView = view.findViewById(R.id.rvListOrder1);

        iOrderMByStatusController = new OrderMByStatusController(this,view);

        iOrderMByStatusController.getListOrderByStatus("DANGXULY");

        mappingInformationCustomer(view);

        nonActiveButton(btProcessing);

    }

    private void mappingInformationCustomer(View view)
    {
        imgbtnadddriver2 = view.findViewById(R.id.imgbtnadddriver2);
        btProcessing = view.findViewById(R.id.buttonProcessing);
        btDelivering = view.findViewById(R.id.buttonDelivering);
        btDelivered = view.findViewById(R.id.buttonDelivered);
        btCancel = view.findViewById(R.id.buttonCancel);

        btProcessing.setOnClickListener(v-> {
            loadRecyclerViewByStatus("DANGXULY");
        });

        btDelivering.setOnClickListener(v-> {
            loadRecyclerViewByStatus("DANGGIAO");
        });

        btDelivered.setOnClickListener(v-> {
            loadRecyclerViewByStatus("DAGIAO");
        });

        btCancel.setOnClickListener(v-> {
            loadRecyclerViewByStatus("DAHUY");
        });

        imgbtnadddriver2.setOnClickListener(v->{
            detailOrder(-1);
        });
    }
    @SuppressLint("ResourceAsColor")
    void nonActiveButton(Button bt)
    {
        btProcessing.setTextColor(R.color.black);
        btDelivered.setTextColor(R.color.black);
        btDelivering.setTextColor(R.color.black);
        btCancel.setTextColor(R.color.black);

        bt.setTextColor(R.color.mycolor);
    }




    @Override
    public void setListOrder(View view, List<Order> listOrder) {
        this.orderList = listOrder;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        if(indexFragment==0)
        {
            orderManagementCustomerAdapter=new OrderManagementCustomerAdapter(true,view, listOrder,recyclerView,iOrderMByStatusController,getActivity(),this);
        }
        else
        {
            orderManagementCustomerAdapter=new OrderManagementCustomerAdapter(false,view, listOrder,recyclerView,iOrderMByStatusController,getActivity(),this);
        }
        recyclerView.setAdapter(orderManagementCustomerAdapter);
        orderManagementCustomerAdapter.notifyDataSetChanged();

        progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
    }


    @Override
    public void detailOrder(int position) {
        if(position==-1)
        {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,new CreateEditOrderCustomer(iMainCustomerActivity));
            fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
            fragmentTransaction.commit();
        }
        else
        {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,new CreateEditOrderCustomer(iMainCustomerActivity, this.orderList.get(position)));
            fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void cancelOrder(String message) {
        orderManagementCustomerAdapter.dismissDialog(message);
    }

    @Override
    public void loadRecyclerViewByStatus(String status) {
            switch (status)
            {
                case "DANGXULY":
                    nonActiveButton(btProcessing);
                    iOrderMByStatusController.getListOrderByStatus("DANGXULY");
                    indexFragment =0;
                    break;
                case "DANGGIAO":
                    nonActiveButton(btDelivering);
                    iOrderMByStatusController.getListOrderByStatus("DANGGIAO");
                    indexFragment =1;
                    break;
                case "DAGIAO":
                    nonActiveButton(btDelivered);
                    iOrderMByStatusController.getListOrderByStatus("DAGIAO");
                    indexFragment =1;
                    break;
                case "DAHUY":
                    nonActiveButton(btCancel);
                    iOrderMByStatusController.getListOrderByStatus("DAHUY");
                    indexFragment =1;
                    break;
            }
    }


}
