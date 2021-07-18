package com.example.TVK.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageCustomerController;
import com.example.TVK.Controller.ManageCustomerController;
import com.example.TVK.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewCustomer extends Fragment  implements IAddNewCustomer{
    Button btnDangky;
    EditText txtcusname_add ,txtcusphone_add,txtcusemail_add ;
    IManageCustomerController iManageCustomerController;
    Button btncancel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewCustomer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewCustomer.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewCustomer newInstance(String param1, String param2) {
        AddNewCustomer fragment = new AddNewCustomer();
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
        View view =  inflater.inflate(R.layout.fragment_add_new_customer, container, false);
        //MainAdminActivity mainAdminActivity = (MainAdminActivity) getActivity();

        iManageCustomerController = new ManageCustomerController(this);
        txtcusname_add = (EditText) view.findViewById(R.id.txtcusname_add);
        txtcusemail_add = (EditText) view.findViewById(R.id.txtcusemail_add);
        txtcusphone_add = (EditText) view.findViewById(R.id.txtcusphone_add);
        btnDangky = (Button) view.findViewById(R.id.btnconfirm_add);
        btncancel = (Button) view.findViewById(R.id.btncancel_add);
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iManageCustomerController.OnCheckData(txtcusname_add,txtcusphone_add, txtcusemail_add, getContext() );


            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ListCustomer()).commit();
            }
        });

        txtcusname_add.setOnFocusChangeListener((v, hasFocus) -> {
           hideKeyboard(v,hasFocus);
        });
        txtcusphone_add.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtcusemail_add.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });


        return view;
    }


    @Override
    public void toastinfor(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public EditText getEdittext(String nameedittext) {
       if (nameedittext.equals("Phone"))
       {
           return txtcusphone_add;
       }
       return null;
    }
    public void sendSms(String toPhoneNumber, String message){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.twilio.com/2010-04-01/Accounts/"+"AC740a4af68031e58bcb1ebedf6d57c1c4"+"/SMS/Messages";
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(("AC740a4af68031e58bcb1ebedf6d57c1c4" + ":" + "f0bbf2e8898c5da0d7fec37dae179a15").getBytes(), Base64.NO_WRAP);

        RequestBody body = new FormBody.Builder()
                .add("From", "+16312121943")
                .add("To", toPhoneNumber)
                .add("Body", message)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", base64EncodedCredentials)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //Log.d(TAG, "sendSms: "+ response.body().string());
        } catch (IOException e)
        { e.printStackTrace(); }

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
        return null;
    }
}