package com.example.TVK.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.TVK.R;
import com.example.TVK.View.IMainCustomerActivity;
import com.example.TVK.View.LoginActivity;

public class CategoryCustomer extends Fragment {
    Button btndangxuat;
    TextView tvNameUser, tvEditProfile;
    ImageButton imgBtOrder;
    IMainCustomerActivity iMainCustomerActivity;

    public CategoryCustomer(IMainCustomerActivity iMainCustomerActivity) {
        this.iMainCustomerActivity = iMainCustomerActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_customer, container, false);
        btndangxuat = view.findViewById(R.id.btndangxuat);
        imgBtOrder = view.findViewById(R.id.imgBtOrder);
        tvNameUser = view.findViewById(R.id.txtnameAd);
        tvEditProfile = view.findViewById(R.id.tvEditProfile);


        tvEditProfile.setOnClickListener(v -> {
            //loadFragment(new EditCustomerInfor());
        });

        imgBtOrder.setOnClickListener(v-> {

        });

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
