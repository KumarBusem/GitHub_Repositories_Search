package com.kumarbusem.githubsearch.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kumarbusem.githubsearch.R;
import com.kumarbusem.githubsearch.activities.RepoInfoActivity;
import com.kumarbusem.githubsearch.adapters.holders.RepositoryHolder;
import com.kumarbusem.githubsearch.model.Repository;
import com.kumarbusem.githubsearch.utility.Constants;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;



public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryHolder> {

    List<Repository> repo = new LinkedList<>();
    Context context;


    public RepositoryAdapter(Context context,List<Repository> repoInfo) {
        repo.addAll(repoInfo);
        this.context = context;
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_adapter , parent , false);
        return new RepositoryHolder(v);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {


            final Repository repository = repo.get(position);
            holder.userName.setText(repository.getFull_name());
            if(repository.getDescription()!=null)
            holder.description.setText(repository.getDescription());
            holder.score.setText(new DecimalFormat("##.##").format(repository.getScore()) + "");
            holder.starsCount.setText(repository.getStargazers_count() + "");
            holder.forksCount.setText(repository.getForks_count() + "");
            Glide.with(context)
                    .load(repository.getOwner().avatar_url)
                    .placeholder(R.drawable.github_logo)
                    .into(holder.userImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = repository.getFull_name();
                    Intent intent = new Intent(context, RepoInfoActivity.class);
                    intent.putExtra(Constants.FULL_NAME, name);
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        if(repo.size()>10)
        return 10;
        else
            return repo.size();
    }

}
