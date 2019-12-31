package com.testing.ApiCalling;


public class ApiConstantClass {

    public static final String DOMAIN_NAME = "http://google.org/";
    public static final String DOMAIN_IMAGE_NAME = "http://google.co.in/";



    public static final String GET_CATEGORY_LIST = "get__list";


    public static final int GET_CATEGORY_LIST_ID = 1;


    public static String GetSoapServicePath(String requestType)
    {
        return DOMAIN_NAME + requestType;
    }

}
