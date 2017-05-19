package com.tayyabarain.tcube.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tayyabarain.tcube.R;
import com.tayyabarain.tcube.model.ProductSubCategory;
import com.tayyabarain.tcube.model.ScreenLevel;
import com.tayyabarain.tcube.model.ScreenLevelResponce;
import com.tayyabarain.tcube.rest.ApiClient;
import com.tayyabarain.tcube.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenActivity extends AppCompatActivity {

    private static final String TAG = ScreenActivity.class.getSimpleName();

    RecyclerView recyclerView;
    List<ScreenLevel> screenlevel;

    List<ProductSubCategory> menulevel;



    Context context;

    Context mContext;
    LinearLayout  popwindowlayout;

   TableViewAdapter mTableViewAdapter;
   PopupRecyclerViewAdapter1 PopupRecyclerViewAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        mContext = getApplicationContext();
        // To fatch value that are comming in intent is
        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String tableId = bundle.getString("tableId");
        Log.d(TAG, " table id in Screen activity "+tableId);

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3)); // define in row how much item You want

        mTableViewAdapter = new TableViewAdapter();
        PopupRecyclerViewAdapter1 = new PopupRecyclerViewAdapter1();
        screenlevel = new ArrayList<>();

        recyclerView.setAdapter(mTableViewAdapter);


        getData();

    }


    public void getData() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ScreenLevelResponce> ScreenResponce = apiService.getScreenLevelMenu();

        ScreenResponce.enqueue(new Callback<ScreenLevelResponce>() {
            @Override
            public void onResponse(Call<ScreenLevelResponce> call, Response<ScreenLevelResponce> response) {

                Log.e(TAG, response.body().toString());
                if (response.body() != null) {
                    if (response.body().getResult() && response.body().getScreenLevels().size() > 0) {
                        List<ScreenLevel> list = response.body().getScreenLevels();

                        Boolean res = response.body().getResult();
                        Log.d(TAG, " RESPONCE  " + res);
                        Log.d(TAG, " RESPONCE SIZE  " + list.size());
                        mTableViewAdapter.notifyDataSetChanged();
                        filterList(list);
                    } else
                        Toast.makeText(context, "No Menu found", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "null found in web response", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ScreenLevelResponce> call, Throwable t) {
                Log.e(TAG, "onFailurre " + t.toString());
            }
        });

    }

    private void filterList(List<ScreenLevel> list) {
        for(int i=0;i<list.size();i++) {
            // if(list.get(i).getShowLogscreen()){
            screenlevel.add(list.get(i));

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

            View view1 = LayoutInflater.from(context).inflate(R.layout.screen_view_item, parent, false);  // here define the xml layout

            VH viewHolder1 = new VH(view1);
//            final Clerk c = clerks.get(viewType);
//            if(!c.getShowLogscreen())
//                view1.setVisibility(View.GONE);

            return viewHolder1;
        }


        @Override
        public void onBindViewHolder(final VH holder, final int position) {
            Log.e(TAG, "position " + position);
            final ScreenLevel sl =screenlevel.get(position);

            holder.textView.setText(sl.getTitle()); // Bind the value to text
            boolean isdeal = sl.getIsPromotion();
            if(!isdeal)
            {
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "It is not deal   Clicked on position " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

                            Log.d(TAG, " Sub category   " + sl.getProductSubCategory());

                       List<ProductSubCategory> submenulist = sl.getProductSubCategory();

                        showPopup(v, submenulist);
                       // filterList2(submenulist);

                    }
                });

            }else{

                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "It is  deal   Clicked on position " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return screenlevel.size();
        }


    }


    // ******************** Code Start For POUPUP Windows **********************************


    public void showPopup(View v ,List<ProductSubCategory> submenulist){

        int[] location = new int[2];
        v.getLocationInWindow(location);
        final View popupView = LayoutInflater.from(mContext).inflate(R.layout.submenu_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //  recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,1));

        List<ProductSubCategory> data ;
        data = new ArrayList<>();
        menulevel = new ArrayList<>();

        for(int i=0;i<submenulist.size();i++) {
//            // if(list.get(i).getShowLogscreen()){
            menulevel.add(submenulist.get(i));
            Log.d(TAG, "  menu add "+submenulist.get(i));
//
             }
       PopupRecyclerViewAdapter1.notifyDataSetChanged();

//        data.add("my data");
//        data.add("my test data");
//        data.add("my test data2");
//        data.add("my test data3");
//        data.add("my test data4");
//        data.add("my test data5");
//        data.add("my test data6");
//        data.add("my test data7");
//        data.add("my test data8");
//        data.add("my test data9");

        PopupRecyclerViewAdapter1 adapter = new PopupRecyclerViewAdapter1();
        recyclerView.setAdapter(adapter);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY,location[0],location[1]);

    }


//    private void filterList2(List<ProductSubCategory> list) {
//        for(int i=0;i<list.size();i++) {
//            // if(list.get(i).getShowLogscreen()){
//            menulevel.add(list.get(i));
//
//            // }
//        }
//        PopupRecyclerViewAdapter1.notifyDataSetChanged();
//    }



    //View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.popitem);
        }
    }

    public class PopupRecyclerViewAdapter1 extends RecyclerView.Adapter<MyViewHolder> {

        private Context mContext;
        private ArrayList<String> data;



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.submenu_item, parent,false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final ProductSubCategory submenulevel =menulevel.get(position);

           holder.mTextView.setText(submenulevel.getTitle());
        }

        @Override
        public int getItemCount() {
            return menulevel.size();
        }



    }


}
