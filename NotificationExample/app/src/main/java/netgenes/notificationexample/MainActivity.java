package netgenes.notificationexample;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.GregorianCalendar;

public class MainActivity extends ActionBarActivity {

    Button showNotificationBut, stopNotificationBut, alertButton;

    NotificationManager notificationManager;

    // Boolean to track if there is a notification in the task bar already
    boolean isNotificActive = false;

    int notifID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotificationBut = (Button) findViewById(R.id.showNotificationBut);
        showNotificationBut = (Button) findViewById(R.id.stopNotificationBut);
        showNotificationBut = (Button) findViewById(R.id.alertButton);

    }

    public void showNotification(View view){


        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new
                NotificationCompat.Builder(this)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert New Message")
                .setSmallIcon(R.drawable.dice_logo_24_24);

        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        // Build a task stack so when the user hits the back button it goes to the proper place
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);

        tStackBuilder.addParentStack(MoreInfoNotification.class);
        tStackBuilder.addNextIntent(moreInfoIntent);

        // Define an intent and an action to preform with that intent by another application
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Define the intent the fire when the notification is clicked on
        notificationBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID, notificationBuilder.build());

        // Set to true since notification is active
        isNotificActive = true;
    }

    public void stopNotification(View view){
        if(isNotificActive){
            // Close the notification, pass in the ID so it knows to close the correct notification
            Log.d("message","it gets here");
            notificationManager.cancel(notifID);
        }
    }

    // Method called when the Set Alarm button is pressed
    public void setAlarm(View view){

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));
    }

}
