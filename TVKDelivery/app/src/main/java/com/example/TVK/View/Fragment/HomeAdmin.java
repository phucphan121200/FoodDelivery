package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.TVK.R;


public class HomeAdmin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeAdmin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeAdmin.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeAdmin newInstance(String param1, String param2) {
        HomeAdmin fragment = new HomeAdmin();
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
        View view=  inflater.inflate(R.layout.fragment_home_admin, container, false);
        ImageButton btnDriver = (ImageButton) view.findViewById(R.id.btnHomeDriver);
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListDriver();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

            }
        });
        ImageButton btnCustomer = (ImageButton) view.findViewById(R.id.btnHomeCustomer);
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListCustomer();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });
        ImageButton btnContact = (ImageButton) view.findViewById(R.id.btnHomeContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListContact();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        ImageButton btnFeedback = (ImageButton) view.findViewById(R.id.btnHomeFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListFeedback();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });


        return view;
    }
}