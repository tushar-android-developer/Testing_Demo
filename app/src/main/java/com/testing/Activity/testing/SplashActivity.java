package com.testing.Activity.testing;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;

import com.testing.ApiCalling.VollyApiActivity;
import com.testing.R;
import com.testing.network.SuperActivity;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class SplashActivity extends SuperActivity {

    String regid="";
    String id="";
    private Context context;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For Android Nougat and above
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_splash);

        context=this;

        //regid = ""+ FirebaseInstanceId.getInstance().getToken();
        Log.v("tushar","regid : "+regid);


        //For Changing status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.red_gradient_1));
        }

        keyhash();
        context=this;
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                //Intent intent = new Intent(SplashActivity.this, NewsCategoryListActivity.class);
                Intent intent = new Intent(SplashActivity.this, SplashActivity.class);
                //Intent intent = new Intent(SplashActivity.this, TermsConditionsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();

            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void getFinalLogger(JSONObject jsonObject, int service_ID) {

    }


    public void keyhash(){

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.my", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("tushar", "KeyHash : "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }



    //For Api Calling
    VollyApiActivity vollyApiActivity;
    HashMap<String, String> urlParameter;

    //For Api Call
    /*public void onBindData(boolean showLoader) {

        if (Constant.isNetworkAvailable(context))
        {
            urlParameter = new HashMap<String, String>();
            urlParameter.put("user_id", ""+ CommonUtility.getGlobalString(activity, "user_id"));

            vollyApiActivity = null;
            vollyApiActivity = new VollyApiActivity(context, this,
                    urlParameter, ApiConstantClass.GET_CATEGORY_NEWS, ApiConstantClass.GET_CATEGORY_NEWS_ID,showLoader);

        } else {
            Toast.makeText(context, Constant.MSG_INTERNETERROR, Toast.LENGTH_SHORT).show();
            error_tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.img_no_internet, 0, 0);
            error_tv.setVisibility(View.VISIBLE);
            error_tv.setText(""+Constant.NO_INTERNET);
            recylerview.setVisibility(View.GONE);
        }
    }*/

    @Override
    public void getSuccessResponce(JSONObject jsonObject, int service_ID) {
/*
        try {
            Log.v("tushar", "GET_ADVERTISEMENT_LIST_ID" + jsonObject);

            //JSONObject jsonObjectData = jsonobj.optJSONObject("data");
            JSONObject jsonObjectData = jsonObject;
            String message = jsonObjectData.optString("msg");

            switch (service_ID) {
                case ApiConstantClass.GET_ADVERTISEMENT_LIST_ID:

                    if (jsonObjectData.optString("result").equalsIgnoreCase("1"))
                    {
                        JSONArray jsonArrayDetails = jsonObjectData.optJSONArray("data");

                        if(jsonArrayDetails!=null && jsonArrayDetails.length()>0)
                        {
                            image_url = jsonArrayDetails.optJSONObject(0).optString("image_url");
                            website_url = jsonArrayDetails.optJSONObject(0).optString("website_url");

                            if (!image_url.equalsIgnoreCase(""))
                            {
                                Picasso.with(context)
                                        .load(""+image_url.replaceAll(" ", "%20"))
                                        .error(R.mipmap.ic_launcher)
                                        .placeholder(R.mipmap.ic_launcher)
                                        //.resize(800,800)
                                        //.centerCrop()
                                        .into(adView);
                            } else {}
                        }
                    }

                    else if (jsonObjectData.optString("result").equalsIgnoreCase("4"))
                    {
                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();

                        CommonUtility.setGlobalString(context, "user_id", "");
                        CommonUtility.clear(context);

                        Intent _intent = new Intent(context, SplashActivity.class);
                        _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        _intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(_intent);
                        finish();
                    }
                    else {
                        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }

    @Override
    public void getErrorResponce(String error, int service_ID) {

    }
}
