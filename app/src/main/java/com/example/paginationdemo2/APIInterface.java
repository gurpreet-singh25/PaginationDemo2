package com.example.paginationdemo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/photos")
    Call<List<Result>> getdata();

}
