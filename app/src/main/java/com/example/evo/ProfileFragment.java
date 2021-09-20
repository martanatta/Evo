package com.example.evo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.evo.EditProfileActivity.Name;
import static com.example.evo.EditProfileActivity.Surname;
import static com.example.evo.MainActivity.musicFiles;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    SoundsAdapter musicAdapter;
    Button editProfile;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.profile_edit);
        editProfile.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        if (!(musicFiles.size() < 1)) {
//            musicAdapter = new SoundsAdapter(getContext(), musicFiles);
//            recyclerView.setAdapter(musicAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

            TextView nameSP = view.findViewById(R.id.users_name);
            nameSP.setText(Name + " " + Surname);
//        }
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }
}