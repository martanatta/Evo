package kg.evo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kg.evo.RegUser.SignUpActivity;

public class LogInSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);
    }

    public void enter_by_email(View view) {
        Intent intent = new Intent(LogInSignUpActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    public void sign_up_from_log_in_sign_up(View view) {
        Intent intent = new Intent(LogInSignUpActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void enter_without_auth(View view) {
        Intent intent = new Intent(LogInSignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}