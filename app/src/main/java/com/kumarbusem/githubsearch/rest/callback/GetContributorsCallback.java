package com.kumarbusem.githubsearch.rest.callback;

import com.kumarbusem.githubsearch.model.Contributors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetContributorsCallback implements Callback<List<Contributors>> {



    public interface GetContributorsCallbackListener {
        void onPostedSuccess(List<Contributors> contributorsList);
        void onPostFailure();
    }

    private GetContributorsCallbackListener listener;

    public GetContributorsCallback(GetContributorsCallbackListener listener){
      this.listener = listener;
    }

    @Override
    public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {
        if(response.isSuccessful() && (response.code() == 200 || response.code() == 201)){
            listener.onPostedSuccess(response.body());
        }
        else{
            listener.onPostFailure();
        }
    }

    @Override
    public void onFailure(Call<List<Contributors>> call, Throwable t) {

    }
}
