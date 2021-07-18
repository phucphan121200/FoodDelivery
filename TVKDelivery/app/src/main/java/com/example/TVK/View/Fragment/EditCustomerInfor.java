package com.example.TVK.View.Fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.EditInforCustomerController;
import com.example.TVK.Controller.IEditInforCustomerController;
import com.example.TVK.Controller.ManageCustomerController;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.IMainCustomerActivity;

public class EditCustomerInfor extends Fragment implements IEditCustomerInfor {
    IMainCustomerActivity iMainCustomerActivity;
    EditText txtname, txtphone, txtemail, txtaddr;
    ImageButton btnback;
    RadioButton chkMale, chkFemale;
    Switch notification;
    Dialog dialog;
    public static final String TAG = DetailInforCustomer.class.getName();
    TextView textView;
    Button cancel,ok,btBack, btSave;
    int id;
    String statecus;
    IEditInforCustomerController iEditInforCustomer;
    ProgressDialog progressDialog;
    View view;
    Customer updated_cus = new Customer();

    public EditCustomerInfor(IMainCustomerActivity iMainCustomerActivity) {
        this.iMainCustomerActivity = iMainCustomerActivity;
        // Required empty public constructor
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_infor_customer, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");

        iEditInforCustomer = new EditInforCustomerController(this,view);

        mappingInformationCustomer(view);

        initDialog();

        btnback.setOnClickListener(v -> iMainCustomerActivity.changeFragment(1));

        btSave.setOnClickListener(v -> {
            dialog.show();
        });
        btBack.setOnClickListener(v-> {
            iMainCustomerActivity.changeFragment(1);
        });
    }

    public void initDialog()
    {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.layout_dialog_confirm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations =R.style.animation;
        textView = dialog.findViewById(R.id.txtquestion);
        ok = dialog.findViewById(R.id.dialog_ok);
        cancel = dialog.findViewById(R.id.dialog_cancel);

        textView.setText("Are you sure to save your information ?");
        ok.setOnClickListener(v -> {
            progressDialog.show();
            updated_cus = GlobalUser.getInstanceCustomer("CUSTOMER");
            updated_cus.setFullName(txtname.getText().toString().trim());
            if(chkMale.isChecked())
            {
                updated_cus.setGender("MAL");
            }
            else
            {
                updated_cus.setGender("FEM");
            }

            updated_cus.setPhone(txtphone.getText().toString().trim());
            updated_cus.setAddress(txtaddr.getText().toString().trim());
            updated_cus.setEmail(txtemail.getText().toString().trim());
            if(notification.isChecked())
            {
                updated_cus.setIsReceiveNotification(1);
            }
            else
            {
                updated_cus.setIsReceiveNotification(0);
            }
            iEditInforCustomer.updateInfor(updated_cus);

        });
        cancel.setOnClickListener(v -> dialog.dismiss());
    }
    private void mappingInformationCustomer(View view)
    {
        txtname = (EditText) view.findViewById(R.id.txtcusname);
        chkMale = view.findViewById(R.id.radioButtonMale);
        chkFemale = view.findViewById(R.id.radioButtonFemale);
        txtphone = (EditText) view.findViewById(R.id.txtcusphone);
        txtaddr = (EditText) view.findViewById(R.id.txtcusaddress);
        txtemail = (EditText) view.findViewById(R.id.txtcusemail);
        btnback = (ImageButton) view.findViewById(R.id.btndriverback);
        btBack = view.findViewById(R.id.btBack);
        btSave = view.findViewById(R.id.btSave);
        notification = view.findViewById(R.id.switchstateNotification);

        txtname.setText(GlobalUser.getInstanceCustomer("CUSTOMER").getFullName());
        if(GlobalUser.getInstanceCustomer("CUSTOMER").getGender().equals("MAL"))
        {
            chkMale.setChecked(true);
        }
        else
        {
            chkFemale.setChecked(true);
        }
        txtphone.setText(GlobalUser.getInstanceCustomer("CUSTOMER").getPhone());
        txtaddr.setText(GlobalUser.getInstanceCustomer("CUSTOMER").getAddress());
        txtemail.setText(GlobalUser.getInstanceCustomer("CUSTOMER").getEmail());

        if(GlobalUser.getInstanceCustomer("CUSTOMER").getIsReceiveNotification()==1)
        {
            notification.setChecked(true);
        }else
        {
            notification.setChecked(false);
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
        if(message.equals("Successful"))
        {
            progressDialog.dismiss();
            dialog.dismiss();
            GlobalUser.setCustomerModel(updated_cus);
            iMainCustomerActivity.changeFragment(1);
        }
    }
}
