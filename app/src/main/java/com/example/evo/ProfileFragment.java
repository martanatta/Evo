package com.example.evo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.ApiService;
import com.example.evo.apiShmapi.FavoriteAudioList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View v;
    RecyclerView recyclerView;
    List<FavoriteAudioList> mList;
    AudiosAdapter musicAdapter;
    Button editProfile;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        mList = new ArrayList<>();

        recyclerView = v.findViewById(R.id.recyclerView);


        editProfile = v.findViewById(R.id.profile_edit);
        editProfile.setOnClickListener(this);

        musicAdapter = new AudiosAdapter(mList, getContext());
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        getData();
        return v;
    }

//    public void getData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://a0571908.xsph.ru/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService api = retrofit.create(ApiService.class);
//        api.getFavoriteAudioList().enqueue(new Callback<List<FavoriteAudioList>>() {
//            @Override
//            public void onResponse(Call<List<FavoriteAudioList>> call, Response<List<FavoriteAudioList>> response) {
//                Log.e("onResponse main", "code: " + response.code());
//                Log.e("onResponse main", "string: " + response.toString());
//                if (response.isSuccessful()) {
//                    initData(response.body());
//                } else {
//                    Log.e("not successful", "fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<FavoriteAudioList>> call, Throwable t) {
//                Log.e("onResponse", t.toString());
//            }
//        });
//    }

    private void initData(List<FavoriteAudioList> body) {
        mList.clear();
        mList.addAll(body);
        musicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }
}