package com.michal.lottochecker.mainLotto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiLotto {
    @GET("lotto")
    Call<List<LottoPost>> getPosts();

    @POST("lotto/check")
    Call<LottoDateHelper> getData(@Body LottoDateHelper dataDB);
}
