package com.tayyabarain.tcube.rest;

import com.tayyabarain.tcube.model.ClerkResponce;
import com.tayyabarain.tcube.model.Connect;
import com.tayyabarain.tcube.model.ScreenLevelResponce;
import com.tayyabarain.tcube.model.TableResponce;
import com.tayyabarain.tcube.model.TableResponceRecheck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tayyab Arain on 06/05/2017.
 */
public interface ApiInterface {

    @GET("TcubeukEpos/connect/")
    Call<Connect> checkconnection();

    @GET("TcubeukEpos/getclerks")
    Call<ClerkResponce> getAllClerks();


    @GET("TcubeukEpos/gettables")
    Call<TableResponce> getAllTable();


    @GET("TcubeukEpos/gettable/{id}")
    Call<TableResponceRecheck> getTableRecheck(@Path("id") Integer id);

    // Get Screen Menu
    @GET("TcubeukEpos/GetScreenLevels")
    Call<ScreenLevelResponce> getScreenLevelMenu();
}
