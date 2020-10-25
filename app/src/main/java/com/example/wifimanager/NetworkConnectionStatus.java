package com.example.wifimanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NetworkConnectionStatus extends AppCompatActivity {

    ImageView mConStatusTv;
    TextView mConStatusTv2;
    Button mConStatusBtn;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connection_status);

        ConstraintLayout constraintLayout = findViewById(R.id.layout2);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        mConStatusTv=findViewById(R.id.conStatusTv);
        mConStatusTv2=findViewById(R.id.conStatusTv2);
        mConStatusBtn=findViewById(R.id.conStatusBtn);

        mConStatusBtn.startAnimation(fromtop);

        mConStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkConnectionStatus();
            }
        });
    }
    private void checkNetworkConnectionStatus(){
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr=(ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo=connMgr.getActiveNetworkInfo();
        if(activeInfo != null && activeInfo.isConnected()){
            wifiConnected=activeInfo.getType()==ConnectivityManager.TYPE_WIFI;
            mobileConnected=activeInfo.getType()==ConnectivityManager.TYPE_MOBILE;
            if(wifiConnected){
                mConStatusTv.setImageResource(R.drawable.ic_action_wifi);
                mConStatusTv2.setText("Connected with wifi");

            }else if (mobileConnected){
                mConStatusTv.setImageResource(R.drawable.ic_action_mobile);
                mConStatusTv2.setText("Connected with Mobile Data Connection");

            }
        }else{
            mConStatusTv.setImageResource(R.drawable.ic_action_no);
            mConStatusTv2.setText("No internet connection");

        }




    }
}