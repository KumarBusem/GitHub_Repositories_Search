package com.kumarbusem.githubsearch.rest.api_calls;

import com.kumarbusem.githubsearch.model.RepoObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by achau on 27-12-2017.
 */

public interface GetDefaultRepo {

    @GET("search/repositories")
    Call<RepoObject> getRepository( @QueryMap Map<String, String> options);
}
