package com.trade.imtrade.Lunch_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.trade.imtrade.Authentication.Login;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
                            startActivity(new Intent(Splash_Screen.this, MainActivity.class));
                            finish();

                        }else{
                            startActivity(new Intent(Splash_Screen.this, Login.class));
                            finish();
                        }
                      //  startActivity(new Intent(getApplicationContext(), Login.class));
                      //  finish();
                    }
                }, 1500);

    }
}