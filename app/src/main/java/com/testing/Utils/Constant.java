package com.testing.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint({ "NewApi", "SimpleDateFormat" }) public class Constant {



    public static Context appContext = null;
    public static String _docpicurl=null;

    public static boolean isCommitchatLogin = false;

    public static String SUMMERY = "",is_approved="",is_requested="";

    public static double latitude_current=0.0;
    public static double longitude_current=0.0;


    public static String news_id = "";
    public static String news_title = "";
    public static String news_description = "";
    public static String news_video_url = "";
    public static String news_image_url = "";




    /*Allowed User's Details*/
    public static String allowed_user_id = "";
    public static String allowed_user_image = "";
    public static String allowed_user_name = "";
    public static String allowed_user_tagline = "";


    public static String media_image = "";

    public static String media_title = "";
    public static String category_id = "1";


    public static String media_description = "";
    public static String media_thumb = "";
    public static String media_url = "";
    public static String media_type = "";


    public static final String MSG_INTERNETERROR = "Internet connection is not available.";
    public static final String MSG_SERVER_COMMUNICATION_FALIURE="Server communication failed.";
    public static final String SERVER_ERROR="Oops ! - server not responding, please try after some time.";
    public static final String PHONE_ERROR="Phone number is not available.";


    public static final String NO_INTERNET = "Internet is not connected";



    public static int lazyLoadingLimit = 10;



    public static String getCurrentDeviceTime(){
        // using Calendar class
		/*Calendar ci = Calendar.getInstance();

		String CiDateTime = "" + ci.get(Calendar.YEAR) + "-" +
		    (ci.get(Calendar.MONTH) + 1) + "-" +
		    ci.get(Calendar.DAY_OF_MONTH) + " " +
		    ci.get(Calendar.HOUR) + ":" +
		    ci.get(Calendar.MINUTE) +  ":" +
		    ci.get(Calendar.SECOND);*/

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //String currentDateandTime = sdf.format(new Date());

        // using SimpleDateFormat class
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
        System.out.println(newtime);
        return newtime;
    }



    public static String dateFormat(String Date, String read, String write){
        DateFormat readFormat = new SimpleDateFormat(read);

        DateFormat writeFormat = new SimpleDateFormat(write);
        Date date = null;
        try {
            try {
                date = readFormat.parse( Date );
            } catch (java.text.ParseException e) {

                e.printStackTrace();
            }
        } catch ( ParseException e ) {
            e.printStackTrace();
        }

        String formattedDate = "";
        if( date != null ) {
            formattedDate = writeFormat.format( date );
        }

        System.out.println(formattedDate);

        return formattedDate;
    }





    public static String currentDate() {
		/*Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();*/
        //return calendar.getTime();
		/*Calendar cal = Calendar.getInstance();
		cal.getTime().toString();

		return cal.getTime().toString();*/

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("Z");
        String localTime = date.format(currentLocalTime);
        return localTime.substring(0, 3)+":"+localTime.substring(3, 5);
    }



    public static int dateFormatCompare(String date1 , String date2) {
        //int difference = -1;
        //SimpleDateFormat readDateFormat = new SimpleDateFormat("EEE MMM d ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int difference = -1;
        Date Date1 = null ;
        Date Date2 =  null ;
        try {
            try {
                Date1 = formatter.parse(date1);
                Date2 = formatter.parse(date2);
                long time1 = Date1.getTime();
                long time2 = Date2.getTime();

                if(time1>time2){
                    difference = -1;
                }else if (time1 == time2) {
                    difference = 0;
                }else if (time1<time2) {
                    difference = 1;
                }

            } catch (java.text.ParseException e) {

                e.printStackTrace();
            }
        } catch (ParseException e) {

            e.printStackTrace();
            //   difference = -1 ;
        }

        //difference = Date1.compareTo(Date2);
        return difference ;
    }

    public static Bitmap fixOrientationBitmap(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return bitmap;
        }
        return bitmap;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }


    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }


    public static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    //Hide  Keyboard
    public static void hideKeyBoard(Activity context){
        View focusedView = context.getCurrentFocus();
        //			Toast.makeText(context,"not hide", 1).show();
        if (focusedView != null) {
            //       Toast.makeText(context,"hide", 1).show();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(context.getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }


    //end changes
    public static void hideKeyBord(Context context, View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }



    public static final String NETWORK_NOT_PRESENT = "Your network connection is too slow or may not be working";
    // returns object for myriad pro regular font
    public static Typeface setTypeface1(Context activity) {
        Typeface face = Typeface.createFromAsset(activity.getAssets(),"Roboto-Regular.ttf");
        return face;
    }




    @SuppressLint("SimpleDateFormat")
    public static String setgetDate(String _date)
    {
        if(_date != null && !_date.equals("") && !_date.equals(null) && ! _date.equals("null") && !_date.equals("0000-00-00 00:00:00"))
        {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try
            {
                date = form.parse(_date);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            String newDateStr = postFormater.format(date);
            return newDateStr;
        }
        else
        {
            return "";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String setgetDateAMPM(String _date)
    {
        if(_date != null && !_date.equals("") && !_date.equals(null) && ! _date.equals("null") && !_date.equals("0000-00-00 00:00:00"))
        {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = form.parse(_date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            String newDateStr = postFormater.format(date);
            return newDateStr;
        }
        else
        {
            return "";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String setgetDateAMPMAndMonthInAlphaBets(String _date)
    {
        if(_date != null && !_date.equals("") && !_date.equals(null) && ! _date.equals("null") && !_date.equals("0000-00-00 00:00:00"))
        {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = form.parse(_date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            String newDateStr = postFormater.format(date);
            return newDateStr;
        }
        else
        {
            return "";
        }
    }

    // checks whether string has null or blank value and returns false for null or blank
    public static boolean isStringExists(String str) {
        if (str == null) {
            return false;
        }
        if (!(str instanceof String)) {
            return false;
        }
        if (str.equalsIgnoreCase("null")) {
            return false;
        }
        if (str.equalsIgnoreCase("<null>")) {
            return false;
        }
        if (str.equalsIgnoreCase("(null)")) {
            return false;
        }
        str = str.trim();
        if (str.equals("")) {
            return false;
        }
        return true;
    }

    @SuppressLint("SimpleDateFormat")
    public static String setgetDateOnly(String _date)
    {
        if(_date != null && !_date.equals("") && !_date.equals(null) && ! _date.equals("null") && !_date.equals("0000-00-00 00:00:00"))
        {
            SimpleDateFormat form;
            if(_date.trim().contains(" "))
            {
                form	= new SimpleDateFormat("yyyy-MM-dd");
            }
            else
            {
                form	= new SimpleDateFormat("yyyy-MM-dd");
            }
            //30/04/2015
            Date date = null;
            try {
                date = form.parse(_date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy");
            String newDateStr = postFormater.format(date);
            if(newDateStr == null)
            {
                newDateStr = "";
            }
            return newDateStr;
        }
        else
        {
            return "";
        }
    }

    public static boolean checkEmail(String email) {
        String expression = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*"
                + "+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern emailPattern = Pattern.compile(expression);
        return emailPattern.matcher(email).matches();
    }
    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

}