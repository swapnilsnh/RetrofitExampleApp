package com.example.retrofitexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retrofitexampleapp.api.ApiClient;
import com.example.retrofitexampleapp.api.ApiInterface;
import com.example.retrofitexampleapp.databinding.ActivityMainBinding;
import com.example.retrofitexampleapp.model.MultipleResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        /**
        GET List Resources
        **/
        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                Log.d("snh",response.code()+"");
                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n"+ total +" Total\n"+ totalPages + " Total Pages\n";

                for(MultipleResource.Datum datum : datumList){
                    displayResponse += datum.id +" "+ datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }
                binding.responseTv.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {

            }
        });
    }
}