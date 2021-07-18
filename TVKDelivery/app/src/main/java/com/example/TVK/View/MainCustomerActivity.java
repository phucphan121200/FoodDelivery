package com.example.TVK.View;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.R;
import com.example.TVK.View.Fragment.CategoryCustomer;
import com.example.TVK.View.Fragment.DetailDriver;
import com.example.TVK.View.Fragment.DetailInforCustomer;
import com.example.TVK.View.Fragment.EditCustomerInfor;
import com.example.TVK.View.Fragment.HomeCustomer;
import com.example.TVK.View.Fragment.ListCustomer;
import com.example.TVK.View.Fragment.Notification;
import com.example.TVK.View.Fragment.OrderManagementCustomer;


public class MainCustomerActivity extends AppCompatActivity implements IMainCustomerActivity{
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);

        IMainCustomerActivity iMainCustomerActivity = this;

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_account));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_order));
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_menu));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeCustomer(iMainCustomerActivity,MainCustomerActivity.this);
                        break;
                    case 2:
                        fragment = new EditCustomerInfor(iMainCustomerActivity);
                        break;
                    case 3:
                        fragment =new Notification();
                        break;
                    case 4:
                        fragment = new OrderManagementCustomer(iMainCustomerActivity,MainCustomerActivity.this);
                        break;
                    case 5 :
                        fragment = new CategoryCustomer(iMainCustomerActivity);
                        break;
                    default:
                        fragment = new HomeCustomer(iMainCustomerActivity,MainCustomerActivity.this);
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.setCount(3,"10");
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(),"YouClicked"+ item.getId(), Toast.LENGTH_LONG).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You Reselected" +item.getId(),Toast.LENGTH_LONG).show();
            }
        });



    }


    @Override
    public void getView() {

    }

    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public void gotoDetailFragment(Customer customer)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetailInforCustomer detailInforCustomer = new DetailInforCustomer();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_customer", customer);
        detailInforCustomer.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout,detailInforCustomer);
        fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void sendSms(String toPhoneNumber, String message) {

    }

    public void gotoListCustomer()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListCustomer detailInforCustomer = new ListCustomer();
        fragmentTransaction.replace(R.id.frame_layout,detailInforCustomer);
        //fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
        fragmentTransaction.commit();
    }

    public void gotoDetailFragment(Driver driver)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetailDriver detailDriver = new DetailDriver();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_driver", driver);
        detailDriver.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout,detailDriver);
        fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment(int count) {
        bottomNavigation.show(1,true);
    }
}
