package com.testing.Interface;

import org.json.JSONObject;

public interface JsonResponce {

    public void getSuccessResponce(JSONObject jsonObject, int service_ID);

    public void getErrorResponce(String error, int service_ID);

}