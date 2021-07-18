package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.Model.Feedback;
import com.example.TVK.Model.IFeedback;
import com.example.TVK.R;
import com.example.TVK.View.FeedbackAdapter;
import com.example.TVK.View.Fragment.IListFeedback;
import com.example.TVK.View.IMainAdminActivity;

import java.util.ArrayList;

public class ManageFeedbackController implements IManageFeedbackController {
    IListFeedback iListFeedback;
    IFeedback iFeedback = new Feedback();
    IMainAdminActivity iMainAdminActivity;

    public ManageFeedbackController(IListFeedback iListFeedback) {
        this.iListFeedback = iListFeedback;
    }

    public ManageFeedbackController() {
    }

    @Override
    public FeedbackAdapter loadadapter(Context context) {
        FeedbackAdapter feedbackAdapter;
        ArrayList<Feedback> feedbackArrayList;
        feedbackArrayList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(context, R.layout.layout_custom_feedback, feedbackArrayList, new FeedbackAdapter.IClickItemListener() {
            @Override
            public void onClickItemFeedback(Feedback feedback) {

            }
        });
        iFeedback.getAllDataFeedback(context, feedbackArrayList, feedbackAdapter);
        return feedbackAdapter;
    }

}
