package com.example.TVK.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.TVK.Model.Feedback;
import com.example.TVK.R;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Feedback> feedbackList;
    private FeedbackAdapter.IClickItemListener iClickItemListener;

    public FeedbackAdapter(Context context, int layout, List<Feedback> feedbackList, IClickItemListener iClickItemListener) {
        this.context = context;
        this.layout = layout;
        this.feedbackList = feedbackList;
        this.iClickItemListener = iClickItemListener;
    }

    @Override
    public int getCount() {
        return feedbackList.size();
    }

    public interface IClickItemListener
    {
        void onClickItemFeedback(Feedback feedback);
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedbackAdapter.ViewHolder holder;
        if(convertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtidorder= (TextView) convertView.findViewById(R.id.txtidorder);
            holder.txtnote = (TextView) convertView.findViewById(R.id.txtnote);
            holder.txtrate= (TextView) convertView.findViewById(R.id.txtrate);
            convertView.setTag(holder);
        }
        else {
            holder = (FeedbackAdapter.ViewHolder) convertView.getTag();
        }
        Feedback feedback = feedbackList.get(position);
        holder.txtidorder.setText("ID Order: "+feedback.getIdOrder());
        holder.txtnote.setText("Note: " +feedback.getRating()+"/5");
        holder.txtrate.setText("Rate: "+feedback.getNote());

        holder.txtidorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemFeedback(feedback);
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txtidorder, txtrate, txtnote;
    }
}
