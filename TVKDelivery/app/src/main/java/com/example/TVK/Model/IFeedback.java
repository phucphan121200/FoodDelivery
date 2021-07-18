package com.example.TVK.Model;

import android.content.Context;

import com.example.TVK.View.FeedbackAdapter;

import java.util.ArrayList;

public interface IFeedback {
    void getAllDataFeedback(Context context, ArrayList<Feedback> feedbackArrayList, FeedbackAdapter adapter);
}
