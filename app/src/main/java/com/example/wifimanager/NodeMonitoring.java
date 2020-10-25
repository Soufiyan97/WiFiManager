package com.example.wifimanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class NodeMonitoring extends AppCompatActivity {

    WifiApManager wifiApManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_monitoring);

        ConstraintLayout constraintLayout = findViewById(R.id.layout4);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        wifiApManager = new WifiApManager(this);



        display_active_clients();

    }

    private void display_active_clients() {
        wifiApManager.getClientList(false, new FinishScanListener() {

            @Override
            public void onFinishScan(final ArrayList<ClientScanResult> clients) {
                TextView Node_Monitoring_TextView = (TextView) findViewById(R.id.Node_Monitoring_TextView);
                Node_Monitoring_TextView.append("Connected clients: \n");
                for (ClientScanResult clientScanResult : clients) {
                    Node_Monitoring_TextView.append("------------------------------------\n");
                    Node_Monitoring_TextView.append("IP address : " + clientScanResult.getIP_Address() + "\n");
                    Node_Monitoring_TextView.append("Hardware type : " + clientScanResult.getDevice() + "\n");
                    Node_Monitoring_TextView.append("MAC Address : " + clientScanResult.getHardwareAddress() + "\n");
                    Node_Monitoring_TextView.append("Reachability Status : " + clientScanResult.isReachable() + "\n");
                }
            }
        });
    }
}