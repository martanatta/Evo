package kg.evo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SharedPreferences settings;
    String prefsFiles = "Account";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        settings = getSharedPreferences(prefsFiles, MODE_PRIVATE);
        token = settings.getString("token", "token");
        Log.e("TOKEN TOKEN", token + "ONE TWO");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!token.equals("token")) {
                    Log.e("Token ", " БубенДВА" + token);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    Log.e("Token ", " Бубен Три " + token);
                    Intent intent2 = new Intent(SplashScreenActivity.this, LogInSignUpActivity.class);
                    startActivity(intent2);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}