package com.example.evo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.ApiService;
import com.example.evo.apiShmapi.CategoryDetail;
import com.example.evo.apiShmapi.CategoryList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CategoryDetail.Audio> mList = new ArrayList<>();
    SoundsAdapter musicAdapter;
    CategoryList bebeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        //mList = new ArrayList<CategoryDetail>();

        recyclerView = findViewById(R.id.recyclerView);

        musicAdapter = new SoundsAdapter(mList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(new SoundsActivity()));
        recyclerView.setAdapter(musicAdapter);



        getData();
    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a0571908.xsph.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getCategoryDetail(bebeId.getId()).enqueue(new Callback<CategoryDetail>() {
            @Override
            public void onResponse(Call<CategoryDetail> call, Response<CategoryDetail> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "string: " + response.toString());
                Log.e("work", "code: " + response.body().audios);
                initData(response.body().audios);

            }

            @Override
            public void onFailure(@NonNull Call<CategoryDetail> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
    }

    private void initData(List<CategoryDetail.Audio> audios) {
        Log.e("SMTH", String.valueOf(audios.size()));

        mList.clear();
        mList.addAll(audios);
        musicAdapter.notifyDataSetChanged();
        Log.e("SMTH 2", mList.size() + "");

    }

    public void backOnMain(View view) {
        onBackPressed();
    }
}