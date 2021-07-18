package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageDriverController;
import com.example.TVK.Controller.ManageDriverController;
import com.example.TVK.Model.User.IDriver;
import com.example.TVK.R;
import com.example.TVK.View.DriverAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDriver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDriver extends Fragment implements IListDriver{
    IManageDriverController iManageDriverController;
    IDriver driver;
    ListView listdriver;
    ImageButton btnadd;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListDriver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListDriver.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDriver newInstance(String param1, String param2) {
        ListDriver fragment = new ListDriver();
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
        View view = inflater.inflate(R.layout.fragment_list_driver, container, false);

        btnadd = (ImageButton) view.findViewById(R.id.imgbtnadddriver);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddNewDriver();
                changeFragment(fragment);
            }
        });
        setAdaper(view);

        return view;
    }

    @Override
    public void setAdaper(View view) {
        iManageDriverController = new ManageDriverController(this);
        DriverAdapter adapter = iManageDriverController.loadadapter(getContext());
        listdriver = (ListView) view.findViewById(R.id.listdriver);
        listdriver.setAdapter(adapter);

    }

    @Override
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
}