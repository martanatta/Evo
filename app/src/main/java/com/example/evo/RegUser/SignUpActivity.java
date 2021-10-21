package com.example.evo.RegUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.evo.LogInActivity;
import com.example.evo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Button btnRegister;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    EditText EdTName;
    EditText EdTEmail;
    EditText EdTPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        btnRegister = findViewById(R.id.btnRegister);
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
//        users = db.getReference("Users");
//
//        EdTName = findViewById(R.id.sign_up_name);
//        EdTEmail = findViewById(R.id.sign_up_email);
//        EdTPassword = findViewById(R.id.sign_up_password);
        TextView LogIn = findViewById(R.id.enter_by_email);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(EdTName.getText().toString())) {
//                    EdTName.requestFocus();
//                    EdTName.setError("Напишите свое имя");
//                    return;
//                }
//                if (TextUtils.isEmpty(EdTEmail.getText().toString())) {
//                    EdTEmail.requestFocus();
//                    EdTEmail.setError("Напишите свой Email");
//                    return;
//                }
//                if (TextUtils.isEmpty(EdTPassword.getText().toString())) {
//                    EdTPassword.requestFocus();
//                    EdTPassword.setError("Придумайте пароль");
//                    return;
//
//                } else if (EdTPassword.getText().toString().length() < 6) {
//                    EdTPassword.requestFocus();
//                    EdTPassword.setError("Пароль должен быть длинее 6 символов");
//                    return;
//                }
//
//                mAuth.createUserWithEmailAndPassword(EdTEmail.getText().toString(), EdTPassword.getText().toString())
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//                                finish();
//                                User user = new User();
//                                user.setName(EdTName.getText().toString());
//                                user.setEmail(EdTEmail.getText().toString());
//                                user.setPassword(EdTPassword.getText().toString());
//
//                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                        .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Toast.makeText(SignUpActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(SignUpActivity.this, "Error. " + e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
//
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                finish();
            }
        });
    }

    private void userSignUp() {

        EdTName = findViewById(R.id.sign_up_name);
        EdTEmail = findViewById(R.id.sign_up_email);
        EdTPassword = findViewById(R.id.sign_up_password);

        String name = EdTName.getText().toString().trim();
        String email = EdTEmail.getText().toString().trim();
        String password = EdTPassword.getText().toString().trim();

        Call<ResponseBody> call = RetrofitClient.getInstance()
                .getApi().createUser(name, email, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                response.body();
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "string: " + response.toString());

//                try {
//                    String s = response.body().string();
//                    Toast.makeText(SignUpActivity.this, s , Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}