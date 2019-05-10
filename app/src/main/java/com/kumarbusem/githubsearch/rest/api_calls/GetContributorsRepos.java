package com.kumarbusem.githubsearch.rest.api_calls;

import com.kumarbusem.githubsearch.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by achau on 29-12-2017.
 */

public interface GetContributorsRepos {

    @GET
    Call<List<Repository>> getRepos(@Url String url);
}
