package com.example.hustle4work.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustle4work.R;
import com.example.hustle4work.activity.JobDesc;
import com.example.hustle4work.model.JobFeed;

import java.util.List;

public class jobAdapter extends RecyclerView.Adapter<jobAdapter.JobViewHolder> {


    private Context context;
    List<JobFeed> jobFeedList;
    private LayoutInflater mInflater;



    // constructor for taking values
    public jobAdapter(Context context, List<JobFeed> jobFeedList) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.jobFeedList = jobFeedList;

    }


    @NonNull
    @Override
    public jobAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = mInflater.inflate(R.layout.jobfeeditem, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull jobAdapter.JobViewHolder holder, int position) {

        JobFeed jobFeed = jobFeedList.get(position);

        holder.title.setText(jobFeed.getTitle());
        holder.updatepostontv.setText(jobFeed.getUploadDate());
        holder.locationtv.setText(jobFeed.getLocation());
        holder.payratetv.setText(jobFeed.getPayRate());
        holder.jobtypetv.setText(jobFeed.getJobType());
        holder.cv_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i= new Intent(context , JobDesc.class);
                 i.putExtra("jobFeed", jobFeed);
                 context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return jobFeedList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        TextView title ,updatepostontv ,locationtv ,payratetv ,jobtypetv;
        CardView cv_items;
        public JobViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletv);
            updatepostontv =itemView.findViewById(R.id.updatepostontv);
            locationtv=itemView.findViewById(R.id.locationtv);
            payratetv=itemView.findViewById(R.id.payratetv);
            jobtypetv = itemView.findViewById(R.id.jobtypetv);
            cv_items =itemView.findViewById(R.id.cv_items);


        }
    }
}