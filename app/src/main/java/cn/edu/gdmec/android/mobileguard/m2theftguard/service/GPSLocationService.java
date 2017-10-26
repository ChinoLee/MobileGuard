package cn.edu.gdmec.android.mobileguard.m2theftguard.service;

import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ServiceConfigurationError;

import cn.edu.gdmec.android.mobileguard.m2theftguard.receiver.MyDeviceAdminReceiver;

/**
 * Created by Chino-Lee on 2017/10/26.
 */

public class GPSLocationService extends Service {
    private LocationManager lm;
    private MyListener listener;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new MyListener();
    }

    private class MyListener implements LocationManager {
        //...
    }
}
