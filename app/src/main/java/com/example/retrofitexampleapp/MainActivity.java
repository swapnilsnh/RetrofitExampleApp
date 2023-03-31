package com.example.retrofitexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retrofitexampleapp.api.ApiClient;
import com.example.retrofitexampleapp.api.ApiInterface;
import com.example.retrofitexampleapp.databinding.ActivityMainBinding;
import com.example.retrofitexampleapp.model.MultipleResource;
import com.example.retrofitexampleapp.model.User;
import com.example.retrofitexampleapp.model.UserList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.widget.Toast;

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
                call.cancel();
            }
        });

        /**
         Create New User
         **/
        User user =  new User("Morpheus","Leader");
        Call<User> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1  = response.body();
                Toast.makeText(MainActivity.this, user1.name+ " "+ user1.job, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });

        /**
         Get List Users
         **/
        Call<UserList> call2 =  apiInterface.doGetUserList("2"); //https://reqres.in/api/users?&page=2
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList =  response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList =  userList.data;
                Toast.makeText(MainActivity.this, text+" page\n"+ total+" total\n"+totalPages+" total pages\n", Toast.LENGTH_SHORT).show();
                for (UserList.Datum datum : datumList){
                    Toast.makeText(MainActivity.this, "id: "+datum.id + " name: " + datum.firstName + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call2.cancel();
            }
        });

        /**
         POST name and job Url encoded.
         **/
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;

                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.firstName + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
    }

}