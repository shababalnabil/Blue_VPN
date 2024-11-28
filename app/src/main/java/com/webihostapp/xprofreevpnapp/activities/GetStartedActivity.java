package com.webihostapp.xprofreevpnapp.activities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.webihostapp.xprofreevpnapp.AdSettings;

import com.webihostapp.xprofreevpnapp.Preference;
import com.webihostapp.xprofreevpnapp.customads.AdvertiseWebView;
import com.webihostapp.xprofreevpnapp.MainApp;
import com.webihostapp.xprofreevpnapp.R;

public class GetStartedActivity extends AppCompatActivity {



    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView txtbtn = findViewById(R.id.getstarted_btn);
        TextView rateus = findViewById(R.id.rateus_btn);
        AdvertiseWebView advertiseWebView = findViewById(R.id.advertiseWebView);

        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }


                RelativeLayout nativeAdLayout = findViewById(R.id.getstartednative);
                AdsUtility.loadNativeAd(GetStartedActivity.this, nativeAdLayout, R.layout.native_ad_layout);
            }
        });*/

        advertiseWebView.loadUrl(AdSettings.AD_URL_1);

        Application application = getApplication();
        if (!(application instanceof MainApp)) {
            return;
        }

        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStartedActivity.this, MainActivity.class);
                startActivity(intent);
                new Preference(GetStartedActivity.this).setBooleanpreference("ifsfr", true);
            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

            }

        });

    }
}
