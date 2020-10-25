package com.example.wifimanager;

import java.util.ArrayList;


public interface FinishScanListener {

    public void onFinishScan(ArrayList<ClientScanResult> clients);
}