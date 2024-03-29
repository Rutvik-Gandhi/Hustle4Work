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
import com.example.hustle4work.model.AppliedUser;
import com.example.hustle4work.model.JobFeed;

import java.util.List;

public class AppliedUserAdapter  extends RecyclerView.Adapter<AppliedUserAdapter.JobViewHolder> {


    private Context context;
    List<AppliedUser> jobFeedList;
    private LayoutInflater mInflater;



    // constructor for taking values
    public AppliedUserAdapter(Context context, List<AppliedUser> jobFeedList) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.jobFeedList = jobFeedList;

    }


    @NonNull
    @Override
    public AppliedUserAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = mInflater.inflate(R.layout.jobapplieduser, parent, false);
        return new AppliedUserAdapter.JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedUserAdapter.JobViewHolder holder, int position) {

        AppliedUser jobFeed = jobFeedList.get(position);

       holder.tv_emailApplied.setText("Email : " + jobFeed.getEmail());
        holder.tv_usernameApplied.setText("Username : " + jobFeed.getUsername());
        holder.tv_addressApplied.setText("Address : " + jobFeed.getAddress());






    }

    @Override
    public int getItemCount() {
        return jobFeedList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        TextView tv_emailApplied ,tv_addressApplied ,tv_usernameApplied ;
        CardView cv_items;
        public JobViewHolder(View itemView) {
            super(itemView);
            tv_addressApplied = itemView.findViewById(R.id.tv_addressApplied);
            tv_emailApplied =itemView.findViewById(R.id.tv_emailApplied);
            tv_usernameApplied=itemView.findViewById(R.id.tv_usernameApplied);



        }
    }
}