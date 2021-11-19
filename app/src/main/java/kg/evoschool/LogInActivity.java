package kg.evoschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kg.evoschool.Api.ApiService;
import kg.evoschool.Api.TokenObtainPair;
import kg.evoschool.Api.TokenRefresh;
import kg.evoschool.Api.SignUpActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {
    Button btnEnterToMain;
//    FirebaseAuth mAuth;
//    FirebaseDatabase db;
//    DatabaseReference users;

    EditText EdTEmail;
    EditText EdTPassword;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnEnterToMain = findViewById(R.id.btnEnter);
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
//        users = db.getReference("Users");

        EdTEmail = findViewById(R.id.log_in_email);
        EdTPassword = findViewById(R.id.log_in_password);
        TextView signUp = findViewById(R.id.enter_to_sign_up_from_log_in);

        btnEnterToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(EdTEmail.getText().toString())) {
                    EdTEmail.requestFocus();
                    EdTEmail.setError("Напишите свой Email");
                    return;
                }
                if (TextUtils.isEmpty(EdTPassword.getText().toString())) {
                    EdTPassword.requestFocus();
                    EdTPassword.setError("Напишите свой пароль");
                    return;

                } else if (EdTPassword.getText().toString().length() < 6) {
                    EdTPassword.requestFocus();
                    EdTPassword.setError("Пароль должен быть длинее 6 символов");
                    return;
                }

                Log.e("Check", "checkkkk");
                //getCredentials();
                //Intent intent = new Intent();
//                        intent.putExtra("Email", EdTEmail.getText().toString());
//                        intent.putExtra("Password", EdTPassword.getText().toString());
                if (checkPassword()) {
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

//        mAuth.signInWithEmailAndPassword(EdTEmail.getText().toString(), EdTPassword.getText().toString())
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        startActivity(new Intent(LogInActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(LogInActivity.this, "Error. " + e.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkPassword() {
        email = EdTEmail.getText().toString();
        password = EdTPassword.getText().toString();
        TokenObtainPair tokenObtainPair = new TokenObtainPair(email, password);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://143.198.111.199/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        apiService.logIn(tokenObtainPair).enqueue(new Callback<TokenRefresh>() {
            @Override
            public void onResponse(Call<TokenRefresh> call, Response<TokenRefresh> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", "code: " + response.code());
                    Log.e("onResponse", "string: " + response.toString());
                    Log.e("token", response.body().getAccess());
                    response.body().getAccess();
                    SharedPreferences settings;
                    String prefsFiles = "Account";
                    settings = LogInActivity.this.getSharedPreferences(prefsFiles, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = settings.edit();
                    String token = response.body().getAccess();

                    interceptor.level(HttpLoggingInterceptor.Level.BODY);

                    prefEditor.putString("token", token);
                    prefEditor.apply();
                } else {
                    //Log.e("token", response.body().getAccess());
                }
            }

            @Override
            public void onFailure(Call<TokenRefresh> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
        return true;
    }
}