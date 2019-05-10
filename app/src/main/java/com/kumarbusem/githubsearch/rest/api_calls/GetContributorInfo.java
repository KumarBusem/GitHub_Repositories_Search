package com.kumarbusem.githubsearch.rest.api_calls;

import com.kumarbusem.githubsearch.model.RepoUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface GetContributorInfo {

    @GET
    Call<RepoUser> getContributorInfo(@Url String url);
}
