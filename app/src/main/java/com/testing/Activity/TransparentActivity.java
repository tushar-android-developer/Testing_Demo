package com.testing.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.testing.R;

/**
 * Created by Tushar on 12/12/2017.
 */

public class TransparentActivity extends Activity {

    private TextView tv_content;
    public static Activity ctx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
        tv_content = (TextView) findViewById(R.id.tv_content);

        ctx = TransparentActivity.this;
    }
    public static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    public static void terminateTransparentActivity() {
        if(ctx != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ctx.finish();

                }
            },500);

        }
    }

}