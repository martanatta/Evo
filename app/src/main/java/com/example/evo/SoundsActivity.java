package com.example.evo;

import android.content.Intent;
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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SoundsAdapter musicAdapter;
    public static List<CategoryDetail.Audio> mList = new ArrayList<>();
    private int mMainId;
    int mTrackPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mMainId = extras.getInt("id");
        musicAdapter = new SoundsAdapter(mList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(new SoundsActivity()));
        recyclerView.setAdapter(musicAdapter);
        ItemClickListener listener = new ItemClickListener() {
            @Override
            public void onClick(int position) {
                CategoryDetail.Audio item = mList.get(position);
                Log.e("ПОЗИЦИЯ", position + "");

                Intent intent = new Intent(SoundsActivity.this, PlayerActivity.class);
                intent.putExtra("category_id", mMainId);
                intent.putExtra("sound", item.audio_file);
                intent.putExtra("position", position);
                intent.putExtra("categoryId", mMainId);



                startActivity(intent);
            }
        };
        musicAdapter.setOnItemClickListener(listener);

        getData();
    }

    public void getData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a0571908.xsph.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getCategoryDetail(mMainId).enqueue(new Callback<CategoryDetail>() {
            @Override
            public void onResponse(Call<CategoryDetail> call, Response<CategoryDetail> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "string: " + response.toString());
                Log.e("work", "code: " + response.body().audios);
                interceptor.level(HttpLoggingInterceptor.Level.BODY);
                initData(response.body().audios);
                Log.e("Размер листа", mList.size()+"");
            }

            @Override
            public void onFailure(@NonNull Call<CategoryDetail> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
    }

    private void initData(List<CategoryDetail.Audio> audios) {
        mList.clear();
        mList.addAll(audios);
        musicAdapter.notifyDataSetChanged();
    }

    public void backOnMain(View view) {
        onBackPressed();
    }
}