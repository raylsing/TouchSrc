package raylsing.touchsrc;

import android.app.Application;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
