package com.aeologic.adhoc.qr_utils.utils;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.aeologic.adhoc.qr_utils.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Deepak on 06-Jul-17.
 */

public class Utility extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private ViewGroup contentFrame;

    public static final String QR_CONTENT="QR_CONTENT";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_qr_scanner);
        initViews();
        /*setupToolbar();
         setupStatusBarColor();*/
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    private void initViews() {
        contentFrame = (ViewGroup) findViewById(R.id.content_frame);
    }

    private void setupToolbar() {
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(getString(R.string.qr_scanner));
    }

    private void setupStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(getResources().getColor(R.color.blueDark));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        /*Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();*/
        if (rawResult != null) {
            String qrContent=rawResult.getText();
            Log.v("CONTENT","DATA: "+qrContent);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScannerView.resumeCameraPreview(Utility.this);
                }
            }, 2000);
            Intent intent = new Intent();
            intent.putExtra(QR_CONTENT, qrContent);
            setResult(RESULT_OK, intent);
            finish();

        } else {
            Toast.makeText(Utility.this, getString(R.string.process_failed), Toast.LENGTH_SHORT).show();
            goToBack();
        }
    }

    @Override
    public void onBackPressed() {
        goToBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goToBack();
                break;
        }
        return true;
    }

    private void goToBack() {
        finish();
    }
}