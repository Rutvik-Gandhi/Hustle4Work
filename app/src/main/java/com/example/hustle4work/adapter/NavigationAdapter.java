package com.example.hustle4work.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hustle4work.R;
import com.example.hustle4work.activity.MainActivity;
import com.example.hustle4work.model.NavigationModel;

import java.util.List;

public class NavigationAdapter extends  RecyclerView.Adapter<NavigationAdapter.JobViewHolder> {


   Context context ;
    List<NavigationModel> navigationModels ;
    private LayoutInflater mInflater;
    public NavigationAdapter(Context context, List<NavigationModel> navigationModels) {
         this.navigationModels = navigationModels ;
         this.context =context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NavigationAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nav_recycler_items, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.JobViewHolder holder, int position) {
        NavigationModel navigationModel = navigationModels.get(position);


        holder.textView66.setText(navigationModel.getName());


        // loading album cover using Glide library
        Glide.with(context).load(navigationModel.getImage()).into(holder.iv_nav);
    }

    @Override
    public int getItemCount() {
        return navigationModels.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {

        TextView textView66 ;
        ImageView iv_nav;
         public JobViewHolder(@NonNull View itemView) {
            super(itemView);
             textView66 = itemView.findViewById(R.id.tv_nav);
             iv_nav = itemView.findViewById(R.id.iv_nav);


        }
    }
}
