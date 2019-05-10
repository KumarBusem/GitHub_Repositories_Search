package com.kumarbusem.githubsearch.rest.api_calls;

import com.kumarbusem.githubsearch.model.Contributors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetContributors {

    @GET("repos/{full_name}/contributors")
    Call<List<Contributors>> get(@Path(value = "full_name", encoded = true) String fullName);
}
