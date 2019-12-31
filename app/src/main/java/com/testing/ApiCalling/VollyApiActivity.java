package com.testing.ApiCalling;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.testing.Interface.JsonResponce;
import com.testing.Activity.TransparentActivity;
import com.testing.Utils.Constant;
import com.testing.network.SuperActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class VollyApiActivity {


    ProgressDialog _dialog;
    String _requestType;
    int service_ID = 0;
    SuperActivity _eventLoggerClass;
    JsonResponce jsonResponce;
    String type="";
    HashMap<String, String> _dataToPost;
    Context context;
    boolean isLoaderHide;

    RequestQueue requestQueue;

    public VollyApiActivity(Context con,SuperActivity _baseClass, HashMap<String, String> _dataEntity, String _type, int ID)
    {
        this.context = con;
        this._dataToPost = _dataEntity;
        _eventLoggerClass  = _baseClass;
        _requestType = _type;
        this.service_ID = ID;
        this.type = "Activity";
        executeNetworkCall();
    }

    public VollyApiActivity(Context con,JsonResponce jsonResponce, HashMap<String, String> _dataEntity, String _type, int ID)
    {
        this.context = con;
        this._dataToPost = _dataEntity;
        this.jsonResponce  = jsonResponce;
        _requestType = _type;
        this.service_ID = ID;
        this.type = "Fragment";
        executeNetworkCall();
    }


    /*Just For Hide Loader - For Map */

    public VollyApiActivity(Context con, SuperActivity _baseClass, HashMap<String, String> _dataEntity, String _type, int ID,boolean isLoaderHide)
    {
        this.context = con;
        this._dataToPost = _dataEntity;
        _eventLoggerClass  = _baseClass;
        _requestType = _type;
        this.service_ID = ID;
        this.type = "Activity";
        this.isLoaderHide = isLoaderHide;
        executeNetworkCall();
    }


    public VollyApiActivity(Context con, JsonResponce jsonResponce, HashMap<String, String> _dataEntity, String _type, int ID,boolean isLoaderHide)
    {
        this.context = con;
        this._dataToPost = _dataEntity;
        this.jsonResponce  = jsonResponce;
        _requestType = _type;
        this.service_ID = ID;
        this.type = "Fragment";
        this.isLoaderHide = isLoaderHide;
        executeNetworkCall();

    }


    public void executeNetworkCall()
    {
        Log.v("tushar", "Sending Parameters : " + _dataToPost);

        if (Constant.isNetworkAvailable(context)) {
            callApi();
        } else {
            Toast.makeText(context, Constant.MSG_INTERNETERROR, Toast.LENGTH_SHORT).show();
        }
    }


    public void callApi(){

       /* _dialog = new ProgressDialog(context, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        _dialog.setMessage("Loading...");
        _dialog.isIndeterminate();
        _dialog.setCancelable(false);
        _dialog.show();*/

        if (!isLoaderHide) {
            if (type.equalsIgnoreCase("Activity"))
            {
                if(!TransparentActivity.active){
                    _eventLoggerClass.startActivity(new Intent(_eventLoggerClass, TransparentActivity.class));
                }
            } else {
                if(!TransparentActivity.active){
                    context.startActivity(new Intent(context, TransparentActivity.class));
                }
            }
        } else {
        }

        try {

            requestQueue = Volley.newRequestQueue(context);

            String _url = ApiConstantClass.GetSoapServicePath(_requestType);

            StringRequest jsObjRequest = new StringRequest(Request.Method.POST, _url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    JSONObject _results = null;
                    try {
                        _results = new JSONObject(response);
                        Log.v("tushar", _results.toString(4));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    //_dialog.dismiss();
                    TransparentActivity.terminateTransparentActivity();

                    if (type.equalsIgnoreCase("Activity"))
                    {
                        if (_results != null) {
                            _eventLoggerClass.getSuccessResponce(_results,service_ID);

                        }else{
                            Toast.makeText(context, Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (_results != null) {
                            jsonResponce.getSuccessResponce(_results,service_ID);

                        }else{
                            Toast.makeText(context, Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // handle error response

                    JSONObject _results = null;
                   // _eventLoggerClass.getFinalLogger(_results,service_ID);

                    if (type.equalsIgnoreCase("Activity")) {
                        _eventLoggerClass.getErrorResponce("Error", service_ID);
                    }
                    else{
                        jsonResponce.getSuccessResponce(_results,service_ID);
                    }

                    error.printStackTrace();


                    /*For Print Error */

                    // As of f605da3 the following should work
                    NetworkResponse response = error.networkResponse;
                    if (error instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            // Now you can use any deserializer to make sense of data

                            Log.v("tushar","Error : "+res);
                            JSONObject obj = new JSONObject(res);
                        } catch (UnsupportedEncodingException e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        } catch (JSONException e2) {
                            // returned data is not JSONObject?
                            e2.printStackTrace();
                        }
                    }

                    parseVolleyError(error);
                    Toast.makeText(context, Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();

                    //_dialog.dismiss();
                    TransparentActivity.terminateTransparentActivity();
                }
            })

            {
                @Override
                public byte[] getBody() throws AuthFailureError {

                    Log.e("params", _dataToPost.toString());
                    if (_dataToPost != null && _dataToPost.size() > 0)
                    {
                       // return encodeParameters(_dataToPost, getParamsEncoding());
                    return encodeParameters(_dataToPost, getParamsEncoding());
                    }
                    return null;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                params.put("Content-Type", "application/x-www-form-urlencoded");
                    _dataToPost.put("Apikey", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                    //_dataToPost.put("mobile_auth_token", ""+CommonUtility.getGlobalString(context, "mobile_auth_token"));

                    return _dataToPost;
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset="+ getParamsEncoding();
                    //return "application/x-www-form-urlencoded; Content-Type: application/json; charset="+ getParamsEncoding();
                }

                private byte[] encodeParameters(Map<String, String> params,String paramsEncoding) {
                    StringBuilder encodedParams = new StringBuilder();
                    try {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            encodedParams.append(URLEncoder.encode(entry.getKey(),paramsEncoding));
                            encodedParams.append('=');
                            encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                            encodedParams.append('&');
                        }
                        return encodedParams.toString().getBytes(paramsEncoding);
                    } catch (UnsupportedEncodingException uee) {
                        throw new RuntimeException("Encoding not supported: "+ paramsEncoding, uee);
                    }
                }

            };

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(25000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsObjRequest);
            //jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void parseVolleyError(VolleyError error) {
        try {
            String responseBody = new String(error.networkResponse.data, "utf-8");
            JSONObject data = new JSONObject(responseBody);
            JSONArray errors = data.getJSONArray("errors");
            JSONObject jsonMessage = errors.getJSONObject(0);
            String message = jsonMessage.getString("message");
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Log.v("tushar","errors : "+errors);
            Log.v("tushar","message : "+message);
        } catch (Exception e) {
        }
    }
}
