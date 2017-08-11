package raylsing.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import raylsing.utils.RootShellCmd;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";
    private Handler handler;
    private RootShellCmd cmd;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: executed and start to moni click movition" );
                cmd = new RootShellCmd();
                cmd.clickPoint(200,200);
                Log.e(TAG, "run: click 200 200" );
            }
        },5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }
}
