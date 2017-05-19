package com.tayyabarain.tcube.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tayyabarain.tcube.R;
import com.tayyabarain.tcube.model.Connect;
import com.tayyabarain.tcube.rest.ApiClient;
import com.tayyabarain.tcube.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = (Button) findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ConnectionFunction();

            }
        });
        ConnectionFunction();
}

    public void ConnectionFunction()
    {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Connect> call = apiService.checkconnection();

        call.enqueue(new Callback<Connect>() {
            @Override
            public void onResponse(Call<Connect> call, Response<Connect> response) {
                boolean connection = response.body().getResult();
                String connectionMessage = response.body().getMessage();
                Log.d(TAG, "onResopnse " + connection + "   Message   " + connectionMessage);
                if (connection == true) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Connection Successful  " + connectionMessage, Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), ClerksActivity.class);
                    startActivity(i);

                }


            }

            @Override
            public void onFailure(Call<Connect> call, Throwable t) {
                Log.e(TAG, "onFailurre " + t.toString());

                Context context = getApplicationContext();
                Toast.makeText(context, "please check database connect ", Toast.LENGTH_LONG).show();

            }
        });

    }

}
