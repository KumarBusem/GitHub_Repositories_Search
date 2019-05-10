package com.kumarbusem.githubsearch.rest.api_calls;

import com.kumarbusem.githubsearch.model.RepoUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by achau on 29-12-2017.
 */

public interface GetContributorInfo {

    @GET
    Call<RepoUser> getContributorInfo(@Url String url);
}
