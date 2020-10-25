package com.example.wifimanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SignalStrength extends AppCompatActivity {

    private int signal = 0;
    Button ShowStrength;
    Animation frombottom, fromtop;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_strength);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        ConstraintLayout constraintLayout = findViewById(R.id.layout3);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        ShowStrength=(Button) findViewById(R.id.ShowStrength);
        textView=(TextView) findViewById(R.id.textView);

        ShowStrength.startAnimation(fromtop);
        textView.startAnimation(frombottom);

        ShowStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkConnectionStatus();
            }
        });



    }


    private void checkNetworkConnectionStatus() {
        final TextView textView = (TextView) findViewById(R.id.wifitext);
        System.out.println("Signal strength is : " + signal);
        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int linkSpeed = wifiManager.getConnectionInfo().getRssi();
        System.out.println(linkSpeed);
        textView.setText(linkSpeed + " dBm");
    }
}