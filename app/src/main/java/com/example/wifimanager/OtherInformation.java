package com.example.wifimanager;




import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class OtherInformation extends AppCompatActivity {

    private WifiManager wifiManager;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information);

        ConstraintLayout constraintLayout = findViewById(R.id.layout5);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        TextView MAC_ADDRESS_DISPLAY = (TextView) findViewById(R.id.MAC_ADDRESSS_DISPLAY);
        MAC_ADDRESS_DISPLAY.setText(getWiFiMACAddress());

        TextView WiFi_SPEED_DISPLAY = (TextView) findViewById(R.id.WiFi_SPEED_DISPLAY);
        WiFi_SPEED_DISPLAY.setText(get_wifi_Speed() + " Mbps");

        TextView SSID_DISPLAY = (TextView) findViewById(R.id.SSID_DISPLAY);
        SSID_DISPLAY.setText(get_wifi_SSID());

        TextView BSSID_DISPLAY = (TextView) findViewById(R.id.BSSID_DISPLAY);
        BSSID_DISPLAY.setText(getBSSID());


        TextView Wi_Fi_FREQUENCY_DISPLAY = (TextView) findViewById(R.id.Wi_Fi_FREQUENCY_DISPLAY);
        Wi_Fi_FREQUENCY_DISPLAY.setText(getFrequency());


        TextView WiFi_GATEWAY_DISPLAY = (TextView)findViewById(R.id.WiFi_GATEWAY_DISPLAY);
        WiFi_GATEWAY_DISPLAY.setText(getDefaultGateway());

        TextView WiFi_NETMASK_DISPLAY = (TextView)findViewById(R.id.WiFi_NETMASK_DISPLAY);
        WiFi_NETMASK_DISPLAY.setText(getSubnetMask());
    }

    private String getSubnetMask() {
        DhcpInfo dhcpinfo = wifiManager.getDhcpInfo();
        int SubnetMask = dhcpinfo.netmask;
        return ((SubnetMask) & 0xFF)+"."+
                ((SubnetMask>>>=8)&0xFF)+"."+
                ((SubnetMask>>>=8)&0xFF)+"."+
                (((SubnetMask)>>>=8)&0xFF);
    }

    private String getDefaultGateway(){
        DhcpInfo dhcpinfo = wifiManager.getDhcpInfo();
        int DefaultGateway = dhcpinfo.gateway;
        return (DefaultGateway & 0xFF)+"."+
                ((DefaultGateway>>>=8)&0xFF)+"."+
                ((DefaultGateway>>>=8)&0xFF)+"."+
                ((DefaultGateway>>>=8)&0xFF);
    }

    private String WiFiSignalChannel() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String WiFiFrequencyChannel = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            double Frequency = wifiInfo.getFrequency();
            if ((Frequency>= 2412)&& (Frequency<=2484)) {
                WiFiFrequencyChannel = "2.4GHz";
            } else if (Frequency >= 3657.5 && Frequency <= 3690) {
                WiFiFrequencyChannel = "3.6GHz";
            } else if  (Frequency >= 5825 && Frequency <= 5180) {
                WiFiFrequencyChannel = "5GHz";
            }
        }
        else {
            WiFiFrequencyChannel = "SDK Unsupported";
        }
        return WiFiFrequencyChannel;
    }

    private String WiFiSignalStrength () {
        int wifiState = wifiManager.getWifiState();
        String SignalStrength = "";
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            List<ScanResult> results = wifiManager.getScanResults();
            for (ScanResult result : results) {
                if (result.BSSID.equals(wifiManager.getConnectionInfo().getBSSID())) {
                    int level = WifiManager.calculateSignalLevel(wifiManager.getConnectionInfo().getRssi(), result.level);
                    int difference = ((level) * 100) / (result.level);
                    String deciBelmeters = String.valueOf(result.level);
                    if ((difference>=80)) {
                        SignalStrength = "Excellent " + deciBelmeters + " dBm";
                    } else if ((difference <80) && (difference>=60)) {
                        SignalStrength = "Average " + deciBelmeters + " dBm";
                    } else if ((difference <60) && (difference>=40)) {
                        SignalStrength = "Medium " + deciBelmeters + " dBm";
                    } else if ((difference <40) && (difference>=20)) {
                        SignalStrength = "Weak " + deciBelmeters + " dBm";
                    } else
                        SignalStrength = "Poor " + deciBelmeters + " dBm";
                }
            }
        }
        return SignalStrength;
    }
    private String getWiFiSecurityDetails () {
        String SecurityMode = "";

        List<ScanResult> networkList = wifiManager.getScanResults();
        if (networkList != null) {
            for (ScanResult network : networkList) {
                String Capabilities = network.capabilities;
                String SecurityModes[] = {Constants.WEP, Constants.PSK, Constants.EAP, Constants.OPEN, Constants.WPA2};
                for (int i = 0; i < SecurityModes.length; i++) {
                    if (Capabilities.contains(SecurityModes[i])) {
                        SecurityMode =  SecurityModes[i];
                    }
                }
            }
        }
        return SecurityMode;
    }

    public class Constants {
        public static final String PSK = "PSK";
        public static final String WEP = "WEP";
        public static final String EAP = "EAP";
        public static final String OPEN = "Open";
        public static final String WPA2 = "WPA2";
    }

    public String getWiFiMACAddress()
    {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return(wifiInfo.getMacAddress().toUpperCase());
    }

    public String getFrequency() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String WiFiFrequency = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int Frequency = wifiInfo.getFrequency();
            WiFiFrequency = String.valueOf(Frequency + " MHz");
        } else
            WiFiFrequency = "The SDK unspported";
        return WiFiFrequency;
    }

    public String getBSSID() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String BSSID = wifiInfo.getBSSID();
        return BSSID;
    }

    public int get_wifi_Speed() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int Speed = wifiInfo.getLinkSpeed();
        return Speed;
    }

    public String get_wifi_SSID() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String SSID = wifiInfo.getSSID();
        return SSID;
    }

}


