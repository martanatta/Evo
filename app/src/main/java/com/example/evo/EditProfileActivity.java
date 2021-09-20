package com.example.evo;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static String Name;
    static String Surname;
    static String Country;
    static String City;
    static final String prefsFiles = "Account";
    static final String prefName = "Name";
    static final String prefSurname = "Surname";
    static final String prefEmail = "Email";
    static final String prefDate = "DateOfBirth";
    static final String prefGender = "Gender";
    static final String prefCountry = "Country";
    static final String prefCity = "City";
    static SharedPreferences settings;

    private static final String TAG = "EditProfileActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        settings = this.getSharedPreferences(prefsFiles, MODE_PRIVATE);
        radioGroup = findViewById(R.id.radioGroup);
        mDisplayDate = findViewById(R.id.date_textView);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm//dd//yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void backOnProfile(View view) {
        onBackPressed();
    }

    public void Save(View view) {
        SharedPreferences.Editor prefEditor = settings.edit();

        EditText nameEdit = findViewById(R.id.name_editText);
        Name = nameEdit.getText().toString();
        prefEditor.putString(prefName, Name);

        EditText surnameEdit = findViewById(R.id.surname_editText);
        Surname = surnameEdit.getText().toString();
        prefEditor.putString(prefSurname, Surname);

        EditText cityEdit = findViewById(R.id.city_editText);
        City = cityEdit.getText().toString();
        prefEditor.putString(prefCity, City);

        EditText countryEdit = findViewById(R.id.country_editText);
        Country = countryEdit.getText().toString();
        prefEditor.putString(prefCountry, Country);

        EditText emailEdit = findViewById(R.id.Email_editText);
        String email = emailEdit.getText().toString();
        prefEditor.putString(prefEmail, email);

        TextView dateEdit = findViewById(R.id.date_textView);
        String date = dateEdit.getText().toString();
        prefEditor.putString(prefDate, date);

        RadioGroup genderEdit = findViewById(R.id.radioGroup);
        String gender = genderEdit.toString();
        prefEditor.putString(prefGender, gender);

        prefEditor.commit();
        Log.e("Error", email);

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    protected void onStop() {
        super.onStop();
        Log.e("Error Five", "onStop");
        SharedPreferences.Editor prefEditor = settings.edit();

        EditText nameEdit = findViewById(R.id.name_editText);
        Name = nameEdit.getText().toString();
        prefEditor.putString(prefName, Name);

        EditText surnameEdit = findViewById(R.id.surname_editText);
        Surname = surnameEdit.getText().toString();
        prefEditor.putString(prefSurname, Surname);

        EditText cityEdit = findViewById(R.id.city_editText);
        City = cityEdit.getText().toString();
        prefEditor.putString(prefCity, City);

        EditText countryEdit = findViewById(R.id.country_editText);
        Country = countryEdit.getText().toString();
        prefEditor.putString(prefCountry, Country);

        EditText emailEdit = findViewById(R.id.Email_editText);
        String email = emailEdit.getText().toString();
        prefEditor.putString(prefEmail, email);
        prefEditor.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Error Two", "onStart");
        EditText NameEdit = findViewById(R.id.name_editText);
        EditText SurnameEdit = findViewById(R.id.surname_editText);
        EditText cityEdit = findViewById(R.id.city_editText);
        EditText countryEdit = findViewById(R.id.country_editText);
        TextView dateEdit = findViewById(R.id.date_textView);
        EditText emailEdit = findViewById(R.id.Email_editText);
        RadioGroup genderEdit = findViewById(R.id.radioGroup);

        Name = settings.getString(prefName, "Имя");
        Surname = settings.getString(prefSurname, "Фамилия");
        City = settings.getString(prefCity, "Город");
        Country = settings.getString(prefCountry, "Страна");
        String email = settings.getString(prefEmail, "Почта");
        String date = settings.getString(prefDate, "День");
        String gender = settings.getString(prefGender, "Пол");

        NameEdit.setText(Name);
        SurnameEdit.setText(Surname);
        cityEdit.setText(City);
        countryEdit.setText(Country);
        emailEdit.setText(email);
        dateEdit.setText(date);
        genderEdit.getCheckedRadioButtonId();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}