package com.skripsi.antonprio.pencatatanpersonal.Anggaran;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.skripsi.antonprio.pencatatanpersonal.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anton Prio on 8/18/2016.
 */
public class AnggaranManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String tanggalAlarm = "21/08/2016";
            String jamAlarm = "20:15:00";
            Date date = new Date();
            DateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat jam = new SimpleDateFormat("HH:mm:ss");
            if(tanggal.equals(tanggalAlarm) && jam.equals(jamAlarm)) {
                Intent intent1 = new Intent(context, AnggaranActivity.class);
                createNotif(context, intent1, "Pesan Baru", "Reminder", "Anda Harus Membayar!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNotif(Context context, Intent intent, CharSequence ticker, CharSequence title
    , CharSequence description) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.ic_add_alert_black_24dp);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notification.vibrate = new long[] {150,300,150,400};
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(R.drawable.ic_add_alert_black_24dp, notification);

        try {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
