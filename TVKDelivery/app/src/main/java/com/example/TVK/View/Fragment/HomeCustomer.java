package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.IMainCustomerActivity;
import com.example.TVK.View.Photo;
import com.example.TVK.View.PhotoApdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeCustomer extends Fragment {

    private IMainCustomerActivity iMainCustomerActivity;
    FragmentActivity fragmentActivity;
    public HomeCustomer(IMainCustomerActivity iMainCustomerActivity, FragmentActivity fragmentActivity) {
        this.iMainCustomerActivity = iMainCustomerActivity;
        this.fragmentActivity = fragmentActivity;
    }

    public HomeCustomer()
    {

    }
    private ViewPager viewPager;
    private PhotoApdapter photoApdapter;
    private CircleIndicator circleIndicator;
    private TextView tvWelcomeCustomer;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    private ImageButton btnHomeFeedback,imgButtonOrder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home_customer, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.VpAds);
        circleIndicator = view.findViewById(R.id.circle);
        mListPhoto=getListPhoto();
        photoApdapter = new PhotoApdapter(view.getContext(),mListPhoto);
        viewPager.setAdapter(photoApdapter);
        circleIndicator.setViewPager(viewPager);

        imgButtonOrder = view.findViewById(R.id.imgButtonOrder);
        btnHomeFeedback = view.findViewById(R.id.btnHomeFeedback);

        btnHomeFeedback.setOnClickListener(v -> {
            loadFragment(new OrderManagementCustomer(iMainCustomerActivity,fragmentActivity));
        });
        imgButtonOrder.setOnClickListener(v -> {
            loadFragment(new Notification());
        });

        tvWelcomeCustomer = view.findViewById(R.id.tvWelcomeCustomer);
        tvWelcomeCustomer.setText("Welcome, "+GlobalUser.getInstanceCustomer(null).getFullName());
        photoApdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlideImage();
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private List<Photo> getListPhoto(){
        List<Photo> list= new  ArrayList<>();
        list.add(new Photo(R.drawable.viewpage_1));
        list.add(new Photo(R.drawable.viewpage_2));
        list.add(new Photo(R.drawable.viewpager_3));
        return list;
    }
    private void autoSlideImage(){
        if (mListPhoto==null || mListPhoto.isEmpty() || viewPager==null){
            return;
        }
        if (mTimer==null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem<totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        }else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer!=null){
            mTimer.cancel();
            mTimer=null;
        }
    }
}