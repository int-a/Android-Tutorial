package netgenes.notificationexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Anthony on 1/2/2016.
 */
public class AlertReceiver extends BroadcastReceiver{

    // Called when ever the broadcast is set (when the button is clicked)
    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Times up", "5 Seconds Has Passed", "Alert");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert){

        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class),0);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new
                NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.dice_logo_24_24)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(notificationIntent);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        // Will automatically stop the notification when it is clicked on in the taskbar
        mBuilder.setAutoCancel(true);

        // Notify the user of this background event
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification on the screen
        mNotificationManager.notify(1, mBuilder.build());
    }
}
