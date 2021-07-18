package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageFeedbackController;
import com.example.TVK.Controller.ManageFeedbackController;
import com.example.TVK.Model.IFeedback;
import com.example.TVK.R;
import com.example.TVK.View.FeedbackAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFeedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFeedback extends Fragment implements IListFeedback {

    IManageFeedbackController iManageFeedbackController;
    ListView listfeedback;
    IFeedback iFeedback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFeedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFeedback.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFeedback newInstance(String param1, String param2) {
        ListFeedback fragment = new ListFeedback();
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
        View view = inflater.inflate(R.layout.fragment_list_feedback, container, false);
        setAdaper(view);
        return view;
    }

    @Override
    public void setAdaper(View view) {
        iManageFeedbackController = new ManageFeedbackController(this);
        FeedbackAdapter adapter = iManageFeedbackController.loadadapter(getContext());
        listfeedback = (ListView) view.findViewById(R.id.listfeedback);
        listfeedback.setAdapter(adapter);
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