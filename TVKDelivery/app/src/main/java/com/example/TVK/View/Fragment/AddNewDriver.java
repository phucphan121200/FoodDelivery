package com.example.TVK.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageDriverController;
import com.example.TVK.Controller.ManageDriverController;
import com.example.TVK.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewDriver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewDriver extends Fragment implements IAddNewDriver{
    EditText txtname, txtgender, txtphone, txtemail, txtidnumber, txtlicense, txtaddress;
    Button btnregister, btnback;
    IManageDriverController iManageDriverController;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewDriver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewDriver.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewDriver newInstance(String param1, String param2) {
        AddNewDriver fragment = new AddNewDriver();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_new_driver, container, false);

        iManageDriverController = new ManageDriverController(this);
        txtname = (EditText) view.findViewById(R.id.txtdrivername_add);
        txtgender = (EditText) view.findViewById(R.id.txtdrivergender_add);
        txtemail = (EditText) view.findViewById(R.id.txtdriveremail_add);
        txtphone = (EditText) view.findViewById(R.id.txtdriverphone_add);
        txtidnumber = (EditText) view.findViewById(R.id.txtidnumber_add);
        txtlicense = (EditText) view.findViewById(R.id.txtlicense_add);
        btnregister = (Button) view.findViewById(R.id.btnconfirmdriver_add);
        txtaddress = (EditText) view.findViewById(R.id.txtaddressdriver_add);
        btnback =(Button) view.findViewById(R.id.btncancel_adddriver);

        txtname.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtgender.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtemail.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtphone.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtidnumber.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtlicense.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ListDriver()).commit();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iManageDriverController.OnCheckData(txtname, txtphone, txtgender,txtemail,txtidnumber,txtaddress, txtlicense,getContext());
            }
        });





        return view;
    }

    @Override
    public void toastinfor(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendSms(String toPhoneNumber, String message) {

    }

    @Override
    public void open_dialogLoading(int gravity) {

    }

    @Override
    public void close_dialogLoading() {

    }

    @Override
    public void hideKeyboard(View view, boolean hasFocus) {
        if(!hasFocus) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showToast(String str_error) {

    }

    @Override
    public EditText getEditText(String name_editText) {
        switch (name_editText){
            case "Phone":
                return txtphone;
            case "Idnumber":
                return txtidnumber;
            case "License":
                return txtlicense;
            default:
                return null;
        }
    }
}