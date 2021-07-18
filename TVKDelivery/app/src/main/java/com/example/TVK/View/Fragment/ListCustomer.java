package com.example.TVK.View.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageCustomerController;
import com.example.TVK.Controller.ManageCustomerController;
import com.example.TVK.Model.User.ICustomer;
import com.example.TVK.R;
import com.example.TVK.View.CustomerAdapter;
import com.example.TVK.View.MainAdminActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCustomer extends Fragment implements IListCustomer{
    ListView listCustomer;
    ICustomer iCustomer;
    IManageCustomerController iManageCustomerController;
    ImageButton btnimgplus;
    MainAdminActivity mainAdminActivity;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListCustomer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCustomer.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCustomer newInstance(String param1, String param2) {
        ListCustomer fragment = new ListCustomer();
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
        View view = inflater.inflate(R.layout.fragment_list_customer, container, false);
        //mainAdminActivity = (MainAdminActivity) getActivity();
        btnimgplus = (ImageButton) view.findViewById(R.id.imgbtnaddcus);
        btnimgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddNewCustomer() ;
                changeFragment(fragment);
            }
        });

        listCustomer = (ListView) view.findViewById(R.id.listdriver);
        setAdaper(view);
        return view;
    }


    @Override
    public void setAdaper(View view) {
        iManageCustomerController = new ManageCustomerController(this);
        CustomerAdapter adapter = iManageCustomerController.loadadapter(getContext());
        listCustomer = (ListView) view.findViewById(R.id.listdriver);
        listCustomer.setAdapter(adapter);

    }

    @Override
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }

    @Override
    public void notifyerror(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}