package com.tayyabarain.tcube.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tayyabarain.tcube.R;
import com.tayyabarain.tcube.model.Clerk;
import com.tayyabarain.tcube.model.ClerkResponce;
import com.tayyabarain.tcube.rest.ApiClient;
import com.tayyabarain.tcube.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClerksActivity extends AppCompatActivity {

    private static final String TAG = ClerksActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<Clerk> clerks;
    Context context;
    String[] numbers = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",

    };

    ClerkViewAdapter mClerkViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerks);
        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        mClerkViewAdapter = new ClerkViewAdapter();
        clerks = new ArrayList<>();
        recyclerView.setAdapter(mClerkViewAdapter);
        getData();


    }

    public void getData() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ClerkResponce> clerkResponce = apiService.getAllClerks();

        clerkResponce.enqueue(new Callback<ClerkResponce>() {
            @Override
            public void onResponse(Call<ClerkResponce> call, Response<ClerkResponce> response) {
                if (response.body() != null) {
                    if (response.body().getResult() && response.body().getClerks().size() > 0) {
                        List<Clerk> list = response.body().getClerks();
                        filterList(list);
                    } else
                        Toast.makeText(context, "No clerks found", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "null found in web response", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ClerkResponce> call, Throwable t) {
                Log.e(TAG, "onFailurre " + t.toString());
            }
        });

    }

    // this is used for ignor the clerk which is not for show
    private void filterList(List<Clerk> list) {
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getShowLogscreen()){
                clerks.add(list.get(i));
            }
        }
        mClerkViewAdapter.notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {   //VH is view holder

        public TextView textView;

        public VH(View v) {                  // This method is used for initilize the xml layout field

            super(v);

            textView = (TextView) v.findViewById(R.id.textview1);

        }
    }


    // Adapter

    public class ClerkViewAdapter extends RecyclerView.Adapter<VH> {
        @Override
        public int getItemViewType(int position) {
            return  position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {

            View view1 = LayoutInflater.from(context).inflate(R.layout.clerk_view_item, parent, false);  // here define the xml layout

            VH viewHolder1 = new VH(view1);
//            final Clerk c = clerks.get(viewType);
//            if(!c.getShowLogscreen())
//                view1.setVisibility(View.GONE);

            return viewHolder1;
        }

        @Override
        public void onBindViewHolder(final VH holder, int position) {
            Log.e(TAG, "position " + position);
            final Clerk c = clerks.get(position);

            holder.textView.setText(c.getName()); // Bind the value to text
            final String password = c.getPassword();
            // click able view
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (c.getPasswordprotected()) {

                        // Alert box for password
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Enter Password");

                        // Set up the input
                        final EditText input = new EditText(context);
                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              String pas =   input.getText().toString();
                                if(!pas.isEmpty())
                                {
                                    if(pas.equals(password))
                                    {
                                        Toast.makeText(context, "Password  Match  ", Toast.LENGTH_SHORT).show();

                                        // To Go New Activity
                                        Intent i = new Intent(getApplicationContext(),TableActivity.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "Password Not  Match  ", Toast.LENGTH_LONG).show();
                                    }
                                }
                               // Toast.makeText(context, "Password  "+pas+"  data password  "+password, Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();  //end alert pass
                        Toast.makeText(context, "Password Protected ", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(context, "Not  Password Protected ", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),TableActivity.class);
                        startActivity(i);
                    }

                }
            });

//            holder.textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Clicked on position " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return clerks.size();
        }


    }



}
