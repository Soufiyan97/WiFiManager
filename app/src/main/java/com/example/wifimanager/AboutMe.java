package com.example.wifimanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AboutMe extends AppCompatActivity {

    Animation frombottom, fromtop;
    de.hdodenhof.circleimageview.CircleImageView imageview_profile;
    TextView desc;
    TextView title;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        RelativeLayout constraintLayout = findViewById(R.id.layout6);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        imageview_profile=(de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.imageview_profile);
        desc=(TextView) findViewById(R.id.desc);
        title=(TextView) findViewById(R.id.title);
        linearLayout1=(LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2=(LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout3=(LinearLayout) findViewById(R.id.linearLayout3);

        imageview_profile.startAnimation(fromtop);
        desc.startAnimation(fromtop);
        title.startAnimation(fromtop);
        linearLayout1.startAnimation(frombottom);
        linearLayout2.startAnimation(frombottom);
        linearLayout3.startAnimation(frombottom);
    }
}