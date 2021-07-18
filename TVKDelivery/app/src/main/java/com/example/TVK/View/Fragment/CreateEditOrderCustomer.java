package com.example.TVK.View.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.CreateEditOrderCustomerController;
import com.example.TVK.Controller.ICreateEditOrderCustomerController;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.IMainCustomerActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Float.valueOf;

public class CreateEditOrderCustomer extends Fragment implements ICreateEditOrderCustomer {
    View view;
    ProgressDialog progressDialog;
    IMainCustomerActivity iMainCustomerActivity;
    Button btSave, btBack;
    EditText editPickup, edtReceive, edtMass, edtReceiverName, edtReceiverPhone, edtPostage, edtDescription;
    TextView tvTotal;
    ICreateEditOrderCustomerController iCreateEditOrderCustomerController;
    Order chooseOrder;
    public CreateEditOrderCustomer(IMainCustomerActivity iMainCustomerActivity) {
        this.iMainCustomerActivity = iMainCustomerActivity;
        // Required empty public constructor
    }

    public CreateEditOrderCustomer(IMainCustomerActivity iMainCustomerActivity, Order order) {
        this.iMainCustomerActivity = iMainCustomerActivity;
        this.chooseOrder = order;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_new_order, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        iCreateEditOrderCustomerController = new CreateEditOrderCustomerController(this,view);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");

        mappingInformationCustomer(view);
    }

    private void mappingInformationCustomer(View view)
    {
        btSave = view.findViewById(R.id.btSave);
        btBack = view.findViewById(R.id.btBack);
        editPickup = view.findViewById(R.id.edtPickup);
        edtReceive = view.findViewById(R.id.edtReceive);
        edtReceiverName = view.findViewById(R.id.edtReceiverName);
        edtReceiverPhone = view.findViewById(R.id.edtReceiverPhone);
        edtMass = view.findViewById(R.id.edtMass);
        edtPostage = view.findViewById(R.id.edtPostage);
        edtDescription = view.findViewById(R.id.edtDescription);
        tvTotal = view.findViewById(R.id.tvTotal);

        btBack.setOnClickListener(v -> iMainCustomerActivity.changeFragment(4));

        if(chooseOrder==null)
        {
            edtMass.setOnFocusChangeListener(this::onFocusChange);

            btSave.setOnClickListener(v-> {
                progressDialog.show();
                Order order = new Order();
                order.setIdUser(GlobalUser.getInstanceCustomer("CUSTOMER").getIdUser());
                order.setDeliveryAddress(edtReceive.getText().toString().trim());
                order.setPickupAddress(editPickup.getText().toString().trim());
                order.setMass(Double.parseDouble(edtMass.getText().toString().trim()));
                order.setReceiverName(edtReceiverName.getText().toString().trim());
                order.setReceiverPhone(edtReceiverPhone.getText().toString().trim());
                order.setDescription(edtDescription.getText().toString().trim());
                order.setPostage(Integer.parseInt(edtPostage.getText().toString().trim()));
                order.setTotal(Integer.parseInt(tvTotal.getText().toString().trim()));
                order.setState("DANGXULY");
                Date newDate = new Date();
                order.setStartTime(newDate);
                iCreateEditOrderCustomerController.createOrder(order);
            });
        }
        else
        {
            editPickup.setText(chooseOrder.getPickupAddress());
            edtReceive.setText(chooseOrder.getDeliveryAddress());
            edtReceiverName.setText(chooseOrder.getReceiverName());
            edtReceiverPhone.setText(chooseOrder.getReceiverPhone());
            edtMass.setText(String.valueOf(chooseOrder.getMass()));
            edtPostage.setText(String.valueOf(chooseOrder.getPostage()));
            edtDescription.setText(chooseOrder.getDescription());
            tvTotal.setText(String.valueOf(chooseOrder.getTotal()));

            editPickup.setEnabled(false);
            edtReceive.setEnabled(false);
            edtReceiverName.setEnabled(false);
            edtReceiverPhone.setEnabled(false);
            edtMass.setEnabled(false);
            edtPostage.setEnabled(false);
            edtDescription.setEnabled(false);
            tvTotal.setEnabled(false);
            btSave.setVisibility(View.INVISIBLE);
        }
    }

    private void onFocusChange(View view1, boolean hasFocus) {
        if (!hasFocus) {
            if (edtMass.getText().equals("")) {
                tvTotal.setText("0 VND");
            } else {
                int mass = Integer.parseInt(edtMass.getText().toString().trim()) * 10;
                tvTotal.setText(String.valueOf(mass));
                edtPostage.setText(String.valueOf(mass));
            }
        }
    }

    @Override
    public void showToast(String message) {
        if(message.equals("Successfully"))
        {
            iMainCustomerActivity.changeFragment(1);
            Toast.makeText(view.getContext(),"Order has been created successfully", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(view.getContext(),message, Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }
}
