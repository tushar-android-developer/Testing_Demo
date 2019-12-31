package com.testing;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        /*Boldfonttype*/
        //FontsOverride.setDefaultFont(this, "SERIF", "OpenSans-Bold.ttf");
        /*Regularfonttype*/
      //  FontsOverride.setDefaultFont(this, "MONOSPACE", "TitilliumWeb-Regular.ttf");
        /*SemiBoldfonttype*/
       // FontsOverride.setDefaultFont(this, "SANS_SERIF", "OpenSans-Semibold.ttf");



    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }



}
