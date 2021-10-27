package com.example.evo;

import static com.example.evo.EditProfileActivity.City;
import static com.example.evo.EditProfileActivity.Country;
import static com.example.evo.EditProfileActivity.Name;
import static com.example.evo.EditProfileActivity.Surname;
import static com.example.evo.EditProfileActivity.prefCity;
import static com.example.evo.EditProfileActivity.prefCountry;
import static com.example.evo.EditProfileActivity.prefEmail;
import static com.example.evo.EditProfileActivity.prefName;
import static com.example.evo.EditProfileActivity.prefSurname;
import static com.example.evo.EditProfileActivity.prefsFiles;
import static com.example.evo.EditProfileActivity.settings;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.evo.apiShmapi.AudioList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;

    public static final int REQUEST_CODE = 1;
    static ArrayList<AudioList> musicFiles;
    static boolean shuffleBoolean = false, repeatBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_bn_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_bn_music));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_bn_user));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new SoundsFragment();
                        break;
                    case 2:
                        fragment = new MainFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

        Name = "";
        settings = getSharedPreferences(prefsFiles, EditProfileActivity.MODE_PRIVATE);
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

        } else {
            musicFiles = getAllAudio(this);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE) {
//
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                musicFiles = getAllAudio(this);
//
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
//            }
//        }
//    }

    public static ArrayList<AudioList> getAllAudio(Context context) {
        ArrayList<AudioList> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                AudioList musicFiles = new AudioList(path, title, album, duration);
                Log.e("Path: " + path, "Album: " + album);
                tempAudioList.add(musicFiles);
            }
            cursor.close();
        }
        return tempAudioList;
    }

    private void loadFragment(Fragment fragment) {
        int commit = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    public void enter_in_sound_playlist(View view) {
        Intent intent = new Intent(MainActivity.this, SoundsActivity.class);
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        Log.e("Error Two", "onStart");
        EditText NameEdit = findViewById(R.id.name_editText);
        EditText SurnameEdit = findViewById(R.id.surname_editText);
        EditText CityEdit = findViewById(R.id.city_editText);
        EditText CountryEdit = findViewById(R.id.country_editText);
        EditText emailEdit = findViewById(R.id.Email_editText);
        Name = settings.getString(prefName, "Имя");
        Surname = settings.getString(prefSurname, "Фамилия");
        City = settings.getString(prefCity, "Город");
        Country = settings.getString(prefCountry, "Страна");
        String email = settings.getString(prefEmail, "Почта");
 /*     NameEdit.setText(Name);
        SurnameEdit.setText(Surname);
        emailEdit.setText(email); */
    }
}