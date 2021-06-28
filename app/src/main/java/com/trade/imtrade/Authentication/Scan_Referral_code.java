package com.trade.imtrade.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.google.zxing.Result;
import com.irozon.sneaker.Sneaker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityScanReferralCodeBinding;
import com.trade.imtrade.utils.AppUtils;

public class Scan_Referral_code extends AppCompatActivity implements View.OnClickListener {
ActivityScanReferralCodeBinding binding;
    CodeScanner codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_scan__referral_code);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_scan__referral_code);
        AppUtils.FullScreen(this);
        codeScanner = new CodeScanner(this,binding.scannerView);


        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String s= result.getText();
                       binding.textReferral.setText(s);
                        Sneaker.with(Scan_Referral_code.this)
                                .setTitle("Successfully scan referral code is")
                                .setMessage(s)
                                .setCornerRadius(4)
                                .setDuration(1500)
                                .sneakSuccess();
                    }
                });

            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                Sneaker.with(Scan_Referral_code.this)
                        .setTitle(error.getLocalizedMessage())
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakError();
            }
        });


        binding.scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });


        binding.btnGo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
        requestForCamera();
       // codeScanner.startPreview();
    }


    public void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(Scan_Referral_code.this, "Camera Permission is Required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();

            }
        }).check();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_go:
                MyPreferences.getInstance(Scan_Referral_code.this).putString(PrefConf.REFERRAL_CODE,binding.textReferral.getText().toString());
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                break;
        }

    }
}