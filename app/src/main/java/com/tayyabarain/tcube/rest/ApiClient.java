package com.tayyabarain.tcube.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tayyab Arain on 06/05/2017.
 */
public class ApiClient {
    //192.168.1.104
    public static final String BASE_URL = "http://192.168.1.101:4535/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


// sun aesa kr skp p a
