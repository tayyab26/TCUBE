package com.tayyabarain.tcube.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tayyabarain.tcube.R;
import com.tayyabarain.tcube.model.Table;
import com.tayyabarain.tcube.model.TableResponce;
import com.tayyabarain.tcube.model.TableResponceRecheck;
import com.tayyabarain.tcube.rest.ApiClient;
import com.tayyabarain.tcube.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableActivity extends AppCompatActivity {

    private static final String TAG = TableActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<Table> table;
    Context context;
    private TableResponceRecheck tablecheck;

  TableViewAdapter mTableViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3)); // define in row how much item You want

         mTableViewAdapter = new TableViewAdapter();
         table = new ArrayList<>();
        recyclerView.setAdapter(mTableViewAdapter);

        getData();

    }

    public void getData() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TableResponce> tableResponce = apiService.getAllTable();

        tableResponce.enqueue(new Callback<TableResponce>() {
            @Override
            public void onResponse(Call<TableResponce> call, Response<TableResponce> response) {

                Log.d(TAG, "Null Table  ");


                if (response.body() != null) {
                    if (response.body().getResult() && response.body().getTables().size() > 0) {
                        List<Table> list = response.body().getTables();
                        Boolean res = response.body().getResult();
                        Log.d(TAG, " RESPONCE  " + res);
                        Log.d(TAG, " RESPONCE SIZE  " + list.size());
                        //  mClerkViewAdapter.notifyDataSetChanged();
                        filterList(list);
                    } else
                        Toast.makeText(context, "No Table found", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "null found in web response", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<TableResponce> call, Throwable t) {
                Log.e(TAG, "onFailurre " + t.toString());
            }
        });

    }

    private void filterList(List<Table> list) {
        for(int i=0;i<list.size();i++) {
           // if(list.get(i).getShowLogscreen()){
                table.add(list.get(i));

           // }
        }
        mTableViewAdapter.notifyDataSetChanged();
    }



    public class VH extends RecyclerView.ViewHolder {   //VH is view holder

        public TextView textView;
        public TextView textView2;

        public VH(View v) {                  // This method is used for initilize the xml layout field

            super(v);

            textView = (TextView) v.findViewById(R.id.textview1);
            textView2 = (TextView) v.findViewById(R.id.textview2);

        }
    }


    // Adapter

    public class TableViewAdapter extends RecyclerView.Adapter<VH> {
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

            View view1 = LayoutInflater.from(context).inflate(R.layout.table_view_item, parent, false);  // here define the xml layout

            VH viewHolder1 = new VH(view1);
//            final Clerk c = clerks.get(viewType);
//            if(!c.getShowLogscreen())
//                view1.setVisibility(View.GONE);

            return viewHolder1;
        }


        @Override
        public void onBindViewHolder(final VH holder, int position) {
            Log.e(TAG, "position " + position);
            final Table t = table.get(position);

            holder.textView.setText(t.getTableTitle()); // Bind the value to text

            Boolean tablefree = t.getIsFree();

            if(tablefree)
            {
                holder. textView.setBackgroundResource(R.color.green);
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(context, "Clicked on position " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
                        sendPost(t.getTableId());
                    }
                });



            }else{

               // holder.textView2.setText((int) t.getTotalAmount()); // Bind the value to text
//                holder.textView2.setText("Table Booked"); // Bind the value to text
                holder. textView.setBackgroundResource(R.color.red);

                String  billAmount =   String.valueOf(t.getTotalAmount());
                holder.textView2.setText(billAmount);
                if(t.getBillPrinted())
                {
                    holder. textView.setBackgroundResource(R.color.lightpink);

                }
            }



        }

        @Override
        public int getItemCount() {
            return table.size();
        }


    }

    // andother service in which i pass parameter

    public void sendPost(final Integer tableId)
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getTableRecheck(tableId).enqueue(new Callback<TableResponceRecheck>() {
            @Override
            public void onResponse(Call<TableResponceRecheck> call, Response<TableResponceRecheck> response) {
                Log.e(TAG,response.body().toString());
               boolean result = response.body().getResult();


                if(result)
                {
//                    Table  s =   tablecheck.getTable();
//                    s.getIsFree();

                    Table table = response.body().getTable();
                    Log.d(TAG," responce  "+response.body().toString());
                   boolean free =  table.getIsFree();
                    if(free)
                    {
                       // Toast.makeText(context, "Table is Free  " , Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ScreenActivity.class);

                        // To pass value from one intent to another intent

                        //Create the bundle
                        Bundle bundle = new Bundle();

                        //Add your data to bundle
                        bundle.putString("tableId", String.valueOf(tableId));

                        //Add the bundle to the intent
                        i.putExtras(bundle);


                        startActivity(i);

                    }else{
                        Toast.makeText(context, "Table is not  Free  " , Toast.LENGTH_LONG).show();
                    }

//                    TableRecheck tr = response.body().getTable();
//                    tr.getIsFree();
                     //boolean free = list.getBillPrinted();
//                    Log.d(TAG, " why Crash here   " +list );
                }
               // Toast.makeText(context, "sendPost   Called "+result , Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<TableResponceRecheck> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Toast.makeText(context, "sendPost   Failed ", Toast.LENGTH_LONG).show();

            }
        });
    }

}
