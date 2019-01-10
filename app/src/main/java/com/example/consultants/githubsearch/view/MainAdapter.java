package com.example.consultants.githubsearch.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.consultants.githubsearch.R;
import com.example.consultants.githubsearch.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    List<Repository> items = new ArrayList<>();
    Context context;


    public MainAdapter(List<Repository> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvName;
        TextView tvDescript;
        TextView tvStars;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescript = itemView.findViewById(R.id.tvDescription);
            tvStars = itemView.findViewById(R.id.tvStars);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Repository repository = items.get(position);
        Glide.with(context).load(repository.getAvatarUrl()).into(holder.ivAvatar);
        holder.tvName.setText(repository.getName());
        holder.tvDescript.setText(repository.getDescription());
        holder.tvStars.setText("" + '\u2605' + repository.getStargazersCount());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
