package com.michal.lottochecker.lottoPlus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiLottoPlus {
    @GET("lottoPlus")
    Call<List<LottoPlusPost>> getPostsLottoPlus();

    @POST("lottoPlus/check")
    Call<LottoPlusDateHelper> getDataLottoPlus(@Body LottoPlusDateHelper dataDB);
}
