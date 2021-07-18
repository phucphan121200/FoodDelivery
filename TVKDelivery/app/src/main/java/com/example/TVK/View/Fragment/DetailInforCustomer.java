package com.example.TVK.View.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageCustomerController;
import com.example.TVK.Controller.ManageCustomerController;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailInforCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailInforCustomer extends Fragment {
    EditText txtname, txtgender, txtphone, txtemail, txtaddr, txtusername;
    ImageButton btnback, btnedit;
    Switch state;
    Dialog dialog;
    public static final String TAG = DetailInforCustomer.class.getName();
    TextView textView;
    Button ok ;
    Button cancel;
    int id;
    String statecus;
    IManageCustomerController iManageCustomerController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailInforCustomer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailInforCustomer.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailInforCustomer newInstance(String param1, String param2) {
        DetailInforCustomer fragment = new DetailInforCustomer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail_infor_customer, container, false);

        txtname = (EditText) view.findViewById(R.id.txtcusname);
        txtgender = (EditText) view.findViewById(R.id.txtcusgender);
        txtphone = (EditText) view.findViewById(R.id.txtcusphone);
        txtaddr = (EditText) view.findViewById(R.id.txtcusaddress);
        txtemail = (EditText) view.findViewById(R.id.txtcusemail);
        txtusername = (EditText) view.findViewById(R.id.txtcususername);
        btnback = (ImageButton) view.findViewById(R.id.btndriverback);
        state =(Switch) view.findViewById(R.id.switchstatecustomer);
        initDialog();
        setVisibleFalse();



        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            Customer customer = (Customer) bundle.getSerializable("object_customer");
            if(customer !=null)
            {
                txtname.setText(customer.getFullName());
                txtusername.setText(customer.getUserName());
                txtemail.setText(customer.getEmail());
                txtphone.setText(customer.getPhone());
                txtaddr.setText(customer.getAddress());
                txtgender.setText(customer.getGender());
                if(customer.getStatus().equals("consudung"))
                {
                    state.setChecked(true);
                }else
                {
                    state.setChecked(false);
                }
            }

        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager()!=null)
                {
                    getFragmentManager().popBackStack();
                }
            }
        });
       state.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(state.isChecked() == false)
               {
                   textView.setText("Are you sure you want to block account of this customer?");
                   dialog.show();
               }
               else {
                   textView.setText("Are you sure you want to open account of this customer?");
                   dialog.show();
               }
           }
       });


        return view;
    }

    public void setVisibleFalse()
    {
        txtname.setEnabled(false);
        txtgender.setEnabled(false);
        txtphone.setEnabled(false);
        txtaddr.setEnabled(false);
        txtemail.setEnabled(false);
        txtusername.setEnabled(false);
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
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                iManageCustomerController = new ManageCustomerController();
                if(state.isChecked() == true)
                {
                    statecus ="consudung";
                }else if(state.isChecked() == false)
                {
                    statecus ="block";
                }

                iManageCustomerController.updateStateCustomer(getContext(),statecus,txtphone.getText().toString());

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.isChecked()==false)
                {
                    state.setChecked(true);

                }
                else if(state.isChecked()==true)
                {
                    state.setChecked(false);
                }
                dialog.dismiss();
            }
        });
    }

}