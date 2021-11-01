package com.example.experiments.ItemsCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.experiments.AdpaterClass.AdapterClass;
import com.example.experiments.Generatepojoclasses.Root;
import com.example.experiments.R;
import com.example.experiments.Retrofir.ApiClass;
import com.example.experiments.Retrofir.RetrofitBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsCardname extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_card);
        recyclerView = findViewById(R.id.recyclerView);
        getApi();
    }

    private void getApi() {

        ApiClass apiClass = RetrofitBuilder.getClient().create(ApiClass.class);
        apiClass.getCategories().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.d("reponse",response.body().toString());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                AdapterClass readPdfDashboardAdapter3=new AdapterClass(getApplicationContext(),response.body().getAvailableCategory());
                recyclerView.setAdapter(readPdfDashboardAdapter3);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });

    }
}