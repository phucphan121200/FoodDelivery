package com.example.TVK.View;


import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.R;
import com.example.TVK.View.Fragment.AddNewDriver;
import com.example.TVK.View.Fragment.Category;
import com.example.TVK.View.Fragment.DetailDriver;
import com.example.TVK.View.Fragment.DetailInforCustomer;
import com.example.TVK.View.Fragment.HomeAdmin;
import com.example.TVK.View.Fragment.ListCustomer;
import com.example.TVK.View.Fragment.Notification;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainAdminActivity extends AppCompatActivity implements IMainAdminActivity {
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottomNavigation);

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
                        fragment = new HomeAdmin();
                        break;
                    case 2:
                        fragment = new ListCustomer();
                        break;
                    case 3:
                        fragment =new AddNewDriver();
                        break;
                    case 4:
                        fragment = new Notification();
                        break;
                    case 5 :
                        fragment = new Category();
                        break;
                    default:
                        fragment = new HomeAdmin();
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
    public void gotoListCustomer()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListCustomer detailInforCustomer = new ListCustomer();
        fragmentTransaction.replace(R.id.frame_layout,detailInforCustomer);
        //fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
        fragmentTransaction.commit();
    }
    public void sendSms(String toPhoneNumber, String message){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.twilio.com/2010-04-01/Accounts/AC740a4af68031e58bcb1ebedf6d57c1c4/SMS/Messages";
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(("AC740a4af68031e58bcb1ebedf6d57c1c4" + ":" + "f0bbf2e8898c5da0d7fec37dae179a15").getBytes(), Base64.NO_WRAP);

        RequestBody body = new FormBody.Builder()
                .add("From", "+16312121943")
                .add("To", toPhoneNumber.trim())
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



}
