package com.testing.fcm;

/*import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;*/


public class MyFirebaseMessagingService {/*extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    NotificationCompat.Builder notification;
    String notificationType = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        String event_id="",quote_id="",booking_id="",type="";

        type = ""+ remoteMessage.getData().get("type");

        sendNotification(remoteMessage.getData().get("message"),event_id,quote_id,booking_id,type);
    }


    private void sendNotification(String messageBody,String event_id,String quote_id,String booking_id,String type)
    {
        PendingIntent pendingIntent;
        Intent intent = null;

        //Just for checking which type of notification has come bcoz for open that screen
        if (!CommonUtility.getGlobalString(this,"user_id").equalsIgnoreCase(""))
        {
            CommonUtility.setGlobalString(this,"notification_count","1");

            intent = new Intent(this, SplashActivity.class);
            CommonUtility.setGlobalString(this,"notification_count","0");

            intent.putExtra("comeFrom","notification");

        } else {
            intent = new Intent(this, SplashActivity.class);
        }

        String id = "id_notification_channel";

        if (type.equalsIgnoreCase("session"))
        {

        } else {
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilderOreo = new NotificationCompat.Builder(getApplicationContext(), id);

        notificationBuilderOreo.setSmallIcon(R.mipmap.ic_launcher)//R.drawable.status_icon
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilderOreo.setColor(getResources().getColor(R.color.red_gradient_1));

        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The user-visible name of the channel.
            CharSequence name = ""+getResources().getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(messageBody);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(getResources().getColor(R.color.red_gradient_1));
            notificationManager.createNotificationChannel(mChannel);

            notificationBuilderOreo
                    .setBadgeIconType(R.mipmap.ic_launcher) //your app icon
                    .setChannelId(id)
                    //.setNumber(getNotificationCount() + 1)
                    //.setColor(getResources().getColor(R.color.green_color))
                    .setWhen(System.currentTimeMillis());
        }
        else{
            notificationBuilderOreo.setColor(getResources().getColor(R.color.red_gradient_1));
        }

        //NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilderOreo.build());
    }*/

}