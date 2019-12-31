/*
 * Used as class for common utility functions
 */

package com.testing.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class CommonUtility {

	/**
	 * validate your email address format. Ex-akhi@mani.com
	 */
	public static boolean emailValidator(String email)
	{
		Pattern pattern;
		Matcher matcher;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	// set app level string global value
	public static final boolean setGlobalString(Context context,final String key, final String value) {
		SharedPreferences sharedPref = context.getSharedPreferences("GLOBAL_PREFERENCE", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(key, value);
		editor.commit();

		return true;
	}

	public static void clear(Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences("GLOBAL_PREFERENCE", Context.MODE_PRIVATE);

		//SharedPreferences prefs; // here you get your prefrences by either of two methods
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.commit();

		//return true;
	}

	// get app level string global value
	public static final String getGlobalString(Activity activity,final String key) {
		SharedPreferences sharedPref = activity.getSharedPreferences("GLOBAL_PREFERENCE", Context.MODE_PRIVATE);
		return sharedPref.getString(key, "");
	}

	// get app level string global value
	public static final String getGlobalString(Context activity,
			final String key) {
		SharedPreferences sharedPref = activity.getSharedPreferences("GLOBAL_PREFERENCE", Context.MODE_PRIVATE);
		return sharedPref.getString(key, "");
	}

	// returns userid of loggedin user
	public static final String getUserId(Activity activity) {
		return CommonUtility.getGlobalString(activity, "user_id");
	}

	// returns userid of loggedin user
	public static final String getUserId(Context activity) {
		return CommonUtility.getGlobalString(activity, "user_id");
	}

	// returns Android Device ID
	public static final String getAndroidDeviceID(Context activity) {
		return CommonUtility.getGlobalString(activity, "DEVICE_ID");
	}

	// to check whether network is available or not
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting())
		{
			return true;
		}
		return false;
	}

	public static void hideKeyBoard(Activity ctx){
		InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = ctx.getCurrentFocus();
		if (view != null) {
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}


	public static void hodeKeyboardOnEdittext(Activity ctx,View view){
		InputMethodManager mgr = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}

	/*public static Drawable getColoredBackArrow(Context ctx) {
		Drawable arrowDrawable;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			arrowDrawable = ctx.getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha); // abc_ic_ab_back_material for API 6.0
		} else {
			arrowDrawable = ctx.getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		}

		Drawable wrapped = DrawableCompat.wrap(arrowDrawable);

		if (arrowDrawable != null && wrapped != null) {
			// This should avoid tinting all the arrows
			arrowDrawable.mutate();
			DrawableCompat.setTint(wrapped, Color.BLACK);
		}

		return wrapped;
	}*/


	// converts date string from its current source format to required target
	// format
	// "yyyy-MM-dd HH:mm:ss" to "MM/dd/yyyy hh:mm a"
	// "MM/dd/yy HH:mm:ss" to "MM/dd/yyyy hh:mm a"
	public static String convertDateFormat(String dateStr, String sourceFormat,String targetFormat) {

		if (dateStr.equals("")) {
			return "";
		}
		Log.d("date", dateStr + "---" + sourceFormat + "---" + targetFormat);
		// "yyyy-MM-dd'T'HH:mm:ss.SSS"
		SimpleDateFormat form = new SimpleDateFormat(sourceFormat);
		Date date = null;
		try {
			date = form.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat postFormater = new SimpleDateFormat(targetFormat);
		String newDateStr = postFormater.format(date);
		Log.d("Lead Response", newDateStr);
		return newDateStr;
	}


	// returns current date using format "yyyy-MM-dd HH:mm:ss"
	public static String getCurrentDateTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		return dateFormat.format(new Date());
	}

	public static String getCurrentDay(){
		String goal = "";
		try {
			SimpleDateFormat inFormat = new SimpleDateFormat("dd MMMM yyyy");
			Date date = inFormat.parse(getCurrentDateTime());
			SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
			goal = outFormat.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return goal;
	}


	// Check the start date and end date validation
	public static boolean isDateAfter(String startDate, String endDate)
	{
		try
		{
			String myFormatString = "MM/dd/yyyy"; // for example
			SimpleDateFormat df = new SimpleDateFormat(myFormatString);
			Date date1 = df.parse(endDate);
			Date startingDate = df.parse(startDate);

			if (date1.after(startingDate))
				return true;
			else
				return false;
		}
		catch (Exception e)
		{

			return false;
		}
	}


	public static void populateActionBarWithBack(AppCompatActivity context, Toolbar _toolbar, String _title) {

		context.setSupportActionBar(_toolbar);
		context.getSupportActionBar().setTitle(_title);
		context.getSupportActionBar().setHomeButtonEnabled(true);
		context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}




	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static String getPath(final Context context, final Uri uri)
	{
		final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];


//                    return Environment.getExternalStorageDirectory() + "/" + split[1];
				return getDirectory("SECONDARY_STORAGE", "/sdcard") + "/" + split[1];

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] {
						split[1]
				};

				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}
		return null;
	}


	public static String getDataColumn(Context context, Uri uri, String selection,
									   String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = {
				column
		};

		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
					null);
			if (cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}


	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}
	public static File getDirectory(String variableName, String defaultPath) {
		String path = System.getenv(variableName);
		return path == null ? new File(defaultPath) : new File(path);
	}
	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}


	//For Getting Date OBJ from string
	public static Date getDateFromDateStringFormat(String dateStr, String sourceFormat) {

		// "yyyy-MM-dd'T'HH:mm:ss.SSS"
		SimpleDateFormat form = new SimpleDateFormat(sourceFormat);
		Date date = null;
		try {
			date = form.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}


	public static String checkString(String value)
	{
		String updatedValue = "";
		if(value==null)
		{
			updatedValue = "";
		}
		else if(value.equalsIgnoreCase(""))
		{
			updatedValue = "";
		}
		else if (value.equalsIgnoreCase("null"))
		{
			updatedValue = "";
		}else{
			updatedValue = value;
		}

		return updatedValue;
	}



/*	//For Showing AdMob
	public static void showingAdView(final AdView adView) {
		try {
			AdRequest adRequest = new AdRequest.Builder()
					.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					// Check the LogCat to get your test device ID
					.addTestDevice("C04B1BFFB0774708339BC273F8A43708")
					.build();


			adView.setAdListener(new AdListener() {
				@Override
				public void onAdLoaded() {
					Log.v("tushar", "onAdLoaded");
				}

				@Override
				public void onAdClosed() {
					Log.v("tushar", "onAdClosed");
				}

				@Override
				public void onAdFailedToLoad(int errorCode) {
					//Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
					Log.v("tushar", "onAdFailedToLoad : " + errorCode);
					showingAdView(adView);
				}

				@Override
				public void onAdLeftApplication() {
					//Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
					Log.v("tushar", "onAdLeftApplication");
				}

				@Override
				public void onAdOpened() {
					super.onAdOpened();
				}
			});

			adView.loadAd(adRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	public static void shareApp(Context context,String message)
	{
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		//sendIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=" + context.getPackageName());
		sendIntent.putExtra(Intent.EXTRA_TEXT, ""+message);
		sendIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(sendIntent, "Share via"));
	}


	//For Coverting Time from UTC to Local
	public static String convertDateTimeIntoLocal(String send_time, String sourceFormat, String targetFormat) {

		if (sourceFormat.equalsIgnoreCase("dd-MM-yy") || sourceFormat.equalsIgnoreCase("dd-MM-yyyy") || sourceFormat.equalsIgnoreCase("MM-dd-yy") || sourceFormat.equalsIgnoreCase("MM-dd-yyyy")
				|| sourceFormat.equalsIgnoreCase("yyyy-MM-dd") || sourceFormat.equalsIgnoreCase("yy-MM-dd")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String currentTime = "" + dateFormat.format(new Date());

			send_time = send_time + " " + currentTime;
			sourceFormat = sourceFormat + " HH:mm:ss";
		}

		if (sourceFormat.equalsIgnoreCase("HH:mm:ss") || sourceFormat.equalsIgnoreCase("hh:mm a")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String currentDate = "" + dateFormat.format(new Date());

			send_time = currentDate + " " + send_time;
			sourceFormat = "dd-MM-yyyy " + sourceFormat;
		}


		String formattedDate = "";

		try {
			//  "2018-07-12 13:41:29"
			// String dateStr = "Jul 16, 2013 12:08:59 AM";
			String dateStr = "" + send_time;
			// SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("" + sourceFormat, Locale.ENGLISH);
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date = df.parse(dateStr);
			df.setTimeZone(TimeZone.getDefault());
			formattedDate = df.format(date);

			SimpleDateFormat targetFormate = new SimpleDateFormat("" + targetFormat, Locale.ENGLISH);
			targetFormate.setTimeZone(TimeZone.getDefault());
			formattedDate = targetFormate.format(date);

			Log.v("tushar", "convertDateTimeIntoLocal : " + formattedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formattedDate;
	}


	//For Coverting Time from UTC to Local
	public static String convertDateTimeIntoUTC(String send_time, String sourceFormat, String targetFormat) {
		String formattedDate = "";

		if (sourceFormat.equalsIgnoreCase("dd-MM-yy") || sourceFormat.equalsIgnoreCase("dd-MM-yyyy") || sourceFormat.equalsIgnoreCase("MM-dd-yy") || sourceFormat.equalsIgnoreCase("MM-dd-yyyy")
				|| sourceFormat.equalsIgnoreCase("yyyy-MM-dd") || sourceFormat.equalsIgnoreCase("yy-MM-dd")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			//dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String currentTime = "" + dateFormat.format(new Date());

			send_time = send_time + " " + currentTime;
			sourceFormat = sourceFormat + " HH:mm:ss";
		}

		if (sourceFormat.equalsIgnoreCase("HH:mm:ss") || sourceFormat.equalsIgnoreCase("hh:mm a")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			//dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String currentDate = "" + dateFormat.format(new Date());

			send_time = currentDate + " " + send_time;
			sourceFormat = "dd-MM-yyyy " + sourceFormat;
		}


		try {
			//  "2018-07-12 13:41:29"
			// String dateStr = "Jul 16, 2013 12:08:59 AM";
			String dateStr = "" + send_time;
			//dateStr = "2018-07-21 02:00 am";
			// SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("" + sourceFormat, Locale.ENGLISH);
			df.setTimeZone(TimeZone.getDefault());
			Date date = df.parse(dateStr);
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			formattedDate = df.format(date);


			SimpleDateFormat targetFormate = new SimpleDateFormat("" + targetFormat, Locale.ENGLISH);
			targetFormate.setTimeZone(TimeZone.getTimeZone("UTC"));
			formattedDate = targetFormate.format(date);


			Log.v("tushar", "convertDateTimeIntoUTC : " + formattedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formattedDate;
	}



    public static boolean canAccessCamera(Context context) {
        return((hasPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE))
                && (hasPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE))
                && (hasPermission(context,Manifest.permission.CAMERA)));
    }

    public static  boolean hasPermission(Context context,String perm) {
        return(PackageManager.PERMISSION_GRANTED== ContextCompat.checkSelfPermission(context,perm));
    }




	public static String compressImage(String imageUri) {

		String filename = null;
		try {
			String filePath = imageUri;
			Bitmap scaledBitmap = null;

			BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
			options.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

			int actualHeight = options.outHeight;
			int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

			float maxHeight = 1200.0f;
			float maxWidth = 800.0f;
			float imgRatio = actualWidth / actualHeight;
			float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

			if (actualHeight > maxHeight || actualWidth > maxWidth) {
				if (imgRatio < maxRatio) {
					imgRatio = maxHeight / actualHeight;
					actualWidth = (int) (imgRatio * actualWidth);
					actualHeight = (int) maxHeight;
				} else if (imgRatio > maxRatio) {
					imgRatio = maxWidth / actualWidth;
					actualHeight = (int) (imgRatio * actualHeight);
					actualWidth = (int) maxWidth;
				} else {
					actualHeight = (int) maxHeight;
					actualWidth = (int) maxWidth;

				}
			}

//      setting inSampleSize value allows to load a scaled down version of the original image

			options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
			options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
			options.inPurgeable = true;
			options.inInputShareable = true;
			options.inTempStorage = new byte[16 * 1024];

			try {
				//          load the bitmap from its path
				bmp = BitmapFactory.decodeFile(filePath, options);
			} catch (OutOfMemoryError exception) {
				exception.printStackTrace();

			}
			try {
				scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
			} catch (OutOfMemoryError exception) {
				exception.printStackTrace();
			}

			float ratioX = actualWidth / (float) options.outWidth;
			float ratioY = actualHeight / (float) options.outHeight;
			float middleX = actualWidth / 2.0f;
			float middleY = actualHeight / 2.0f;

			Matrix scaleMatrix = new Matrix();
			scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

			Canvas canvas = new Canvas(scaledBitmap);
			canvas.setMatrix(scaleMatrix);
			canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
			ExifInterface exif;
			try {
				exif = new ExifInterface(filePath);

				int orientation = exif.getAttributeInt(
						ExifInterface.TAG_ORIENTATION, 0);
				Log.d("EXIF", "Exif: " + orientation);
				Matrix matrix = new Matrix();
				if (orientation == 6) {
					matrix.postRotate(90);
					Log.d("EXIF", "Exif: " + orientation);
				} else if (orientation == 3) {
					matrix.postRotate(180);
					Log.d("EXIF", "Exif: " + orientation);
				} else if (orientation == 8) {
					matrix.postRotate(270);
					Log.d("EXIF", "Exif: " + orientation);
				}
				scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
						scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
						true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			FileOutputStream out = null;
			filename = getFilename();
			try {
				out = new FileOutputStream(filename);

				//          write the compressed bitmap at the destination specified by filename.
				scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return filename;

	}

	public static String getFilename()
	{
		String uriSting = null;
		try {
			File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
			if (!file.exists()) {
				file.mkdirs();
			}
			uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uriSting;

	}



	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		int inSampleSize = 0;
		try {
			final int height = options.outHeight;
			final int width = options.outWidth;
			inSampleSize = 1;

			if (height > reqHeight || width > reqWidth) {
				final int heightRatio = Math.round((float) height / (float) reqHeight);
				final int widthRatio = Math.round((float) width / (float) reqWidth);
				inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
			}
			final float totalPixels = width * height;
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;
			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inSampleSize;
	}

}
