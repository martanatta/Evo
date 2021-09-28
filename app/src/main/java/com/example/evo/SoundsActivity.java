package com.example.evo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<AudioListCategory> mList;
    SoundsAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        mList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

//        musicAdapter = new SoundsAdapter(mList);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(new SoundsActivity()));

        getData();
        recyclerView.setHasFixedSize(true);
//        if (!(musicFiles.size() < 1)) {
//            musicAdapter = new SoundsAdapter(this, musicFiles);
//            recyclerView.setAdapter(musicAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        }
    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a0571908.xsph.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getAllSongs().enqueue(new Callback<List<AudioListCategory>>() {
            @Override
            public void onResponse(Call<List<AudioListCategory>> call, Response<List<AudioListCategory>> response) {
//                Log.e("onResponse", "code: " + response.code());
//                Log.e("onResponse", "string: " + response.toString());
//                initData(response.body());
            }

            @Override
            public void onFailure(Call<List<AudioListCategory>> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
    }

//    private void initData(List<AudioList> body) {
//        mList.clear();
//        mList.addAll(body);
//        musicAdapter.notifyDataSetChanged();
//    }

    public void backOnMain(View view) {
        onBackPressed();
    }
}