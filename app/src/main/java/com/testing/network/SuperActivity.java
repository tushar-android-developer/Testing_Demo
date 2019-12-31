package com.testing.network;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.testing.Interface.JsonResponce;
import com.testing.R;
import org.json.JSONObject;


public abstract class SuperActivity extends AppCompatActivity implements JsonResponce {

	public Context _context;
	NetworkConsumer _networkcomsumer;

	public SuperActivity() {

		_networkcomsumer = new NetworkConsumer(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//For Changing status bar color
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.red_gradient_1));
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	abstract public void getFinalLogger(JSONObject jsonObject, int service_ID);


	public NetworkConsumer networkConsumerInstance() {
		return _networkcomsumer;
	}


	public void showToast(String message) {
		Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_SHORT).show();
	}

	public void showLog(String message) {
		Log.v("tushar", ""+message);
	}
}
