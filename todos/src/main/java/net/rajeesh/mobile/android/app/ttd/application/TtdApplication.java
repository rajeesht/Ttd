package net.rajeesh.mobile.android.app.ttd.application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by rtripathi on 11/16/15.
 */
public class TtdApplication extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
