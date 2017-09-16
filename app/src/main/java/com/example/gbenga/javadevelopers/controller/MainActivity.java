package com.example.gbenga.javadevelopers.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbenga.javadevelopers.ItemAdapter;
import com.example.gbenga.javadevelopers.R;
import com.example.gbenga.javadevelopers.api.Client;
import com.example.gbenga.javadevelopers.api.Service;
import com.example.gbenga.javadevelopers.model.Item;
import com.example.gbenga.javadevelopers.model.ItemResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                loadJSON();
            }
        });
    }
    private void initViews(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching data");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.no_connection);

        try{
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<ItemResponse> call = apiService.getItems();
                call.enqueue(new Callback<ItemResponse>() {
                    @Override
                    public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                        List<Item> items = response.body().getItems();
                        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                        recyclerView.smoothScrollToPosition(0);
                        swipeRefreshLayout.setRefreshing(false);
                        progressDialog.hide();

                    }

                    @Override
                    public void onFailure(Call<ItemResponse> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error fetching data!", Toast.LENGTH_SHORT).show();
                        Disconnected.setVisibility(View.VISIBLE);
                        progressDialog.hide();
                    }
                    });
                }catch (Exception e){
            Log.e("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
