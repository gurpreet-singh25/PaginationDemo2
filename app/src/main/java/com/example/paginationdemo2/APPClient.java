package com.example.paginationdemo2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APPClient {
    public static final String BASEURL="https://jsonplaceholder.typicode.com/";

    public static Retrofit getclient()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
