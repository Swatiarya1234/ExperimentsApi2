package com.example.experiments.Retrofir;

import com.example.experiments.Generatepojoclasses.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClass {

    @GET("get_all_lead_type")
    Call<Root> getCategories();
}
