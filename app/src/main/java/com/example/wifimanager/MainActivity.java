package com.example.wifimanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnStrength;
    Button btnNode;
    Button btnOther;
    Button btnExit;
    Button btnAboutMe;
    Animation frombottom, fromtop;
    ImageView bgone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        btnStart=(Button) findViewById(R.id.btnStart);
        btnStrength=(Button) findViewById(R.id.btnStrength);
        btnNode=(Button) findViewById(R.id.btnNode);
        btnOther=(Button) findViewById(R.id.btnOther);
        btnExit=(Button) findViewById(R.id.btnExit);
        btnAboutMe=(Button) findViewById(R.id.btnAboutMe);
        bgone=(ImageView) findViewById(R.id.bgone);

        btnStart.startAnimation(frombottom);
        btnStrength.startAnimation(frombottom);
        btnNode.startAnimation(frombottom);
        btnOther.startAnimation(frombottom);
        btnExit.startAnimation(frombottom);
        btnAboutMe.startAnimation(frombottom);
        bgone.startAnimation(fromtop);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        btnStart.setOnClickListener(btnStartInfo);
        btnStrength.setOnClickListener(btnStrengthInfo);
        btnNode.setOnClickListener(btnNodeInfo);
        btnOther.setOnClickListener(btnOtherInfo);
        btnAboutMe.setOnClickListener(btnAboutMeInfo);


    }

    private View.OnClickListener btnStartInfo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    MainActivity.this, NetworkConnectionStatus.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnStrengthInfo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    MainActivity.this, SignalStrength.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnNodeInfo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    MainActivity.this, NodeMonitoring.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnOtherInfo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    MainActivity.this, OtherInformation.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnAboutMeInfo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    MainActivity.this, AboutMe.class);
            startActivity(intent);
        }
    };

}