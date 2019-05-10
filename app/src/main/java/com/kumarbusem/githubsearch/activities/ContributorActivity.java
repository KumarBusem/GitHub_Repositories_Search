
package com.kumarbusem.githubsearch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kumarbusem.githubsearch.R;
import com.kumarbusem.githubsearch.adapters.RepoAdapter;
import com.kumarbusem.githubsearch.model.RepoUser;
import com.kumarbusem.githubsearch.model.Repository;
import com.kumarbusem.githubsearch.rest.ApiClient;
import com.kumarbusem.githubsearch.rest.api_calls.GetContributorInfo;
import com.kumarbusem.githubsearch.rest.api_calls.GetContributorsRepos;
import com.kumarbusem.githubsearch.rest.callback.GetReposCallback;
import com.kumarbusem.githubsearch.utility.Constants;
import com.kumarbusem.githubsearch.utility.Util;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributorActivity extends AppCompatActivity implements Callback<RepoUser>,
        GetReposCallback.GetReposCallbackListener {

    private TextView name, publicRepos, followers, following ,noRepo;
    private CircleImageView image;
    private RecyclerView recyclerView;
    private String repoUrl, url;
    private RepoAdapter repoAdapter;
    private ProgressBar progressBar;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.mapprr_blue));

        name = findViewById(R.id.ac_name);
        publicRepos = findViewById(R.id.ac_public_repo);
        followers = findViewById(R.id.ac_followers);
        following = findViewById(R.id.ac_following);
        image = findViewById(R.id.ac_image);
        recyclerView = findViewById(R.id.ac_recyclerview);
        noRepo= findViewById(R.id.repo_text);
        progressBar = findViewById(R.id.arw_progress_dialog);
        back = findViewById(R.id.ac_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent i = getIntent();
        url = i.getStringExtra(Constants.GET_REPO_URL);
        repoUrl = i.getStringExtra(Constants.GET_REPOS);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setNestedScrollingEnabled(false);

        if(Util.isNetWorkAvailable(this)) {
            fetchContributorInfo(url);
            fetchRepos(repoUrl);
        }
        else {
            Toast.makeText(this, "Internet not available", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResponse(Call<RepoUser> call, Response<RepoUser> response) {

        if (response.isSuccessful() && (response.code() == 200 || response.code() == 201)) {
            RepoUser repoUser = response.body();
            if (repoUser != null) {
                name.setText(repoUser.getName());
                following.setText(":  " + repoUser.getFollowing() + "");
                followers.setText(":  " + repoUser.getFollowers() + "");
                publicRepos.setText(":  " + repoUser.getPublic_repos() + "");
                Glide.with(this).load(repoUser.getAvatar_url())
                        .placeholder(R.drawable.menu_profile)
                        .into(image);
            } else
                Toast.makeText(this,"No Data Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<RepoUser> call, Throwable t) {

    }

    @Override
    public void onPostedSuccess(List<Repository> repos) {

        if (repos!= null && repos.size() > 0) {
           repoAdapter = new RepoAdapter(this, repos);
            recyclerView.setAdapter(repoAdapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPostFailure() {
        Toast.makeText(this,getString(R.string.error_fetcching),Toast.LENGTH_LONG).show();
    }

    public void fetchContributorInfo(String url) {
        GetContributorInfo apiService = ApiClient.getClient().create(GetContributorInfo.class);
        Call<RepoUser> call = apiService.getContributorInfo(url);
        call.enqueue(this);
    }

    public void fetchRepos(String repoUrl) {

        GetContributorsRepos apiService = ApiClient.getClient().create(GetContributorsRepos.class);
        Call<List<Repository>> call = apiService.getRepos(repoUrl);
        call.enqueue(new GetReposCallback(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
