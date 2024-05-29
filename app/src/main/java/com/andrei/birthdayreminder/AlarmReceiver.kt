package com.andrei.birthdayreminder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.getParcelableExtra<Contact>("contact")

        val mainIntent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)
        if (action?.birthDay != null) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel =
                NotificationChannel("channel", "App", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(context, "channel")
                .setContentTitle("It's ${action.name}'s birthday!")
                .setContentText("Wish them a happy birthday!")
                .setSmallIcon(androidx.core.R.drawable.notification_bg)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_launcher_foreground, "Send Message", pendingIntent)

            sendSMS(context, action.phoneNumber, "Happy Birthday, ${action.name}!")

            notificationManager.notify(1, notification.build())
        }
    }

    private fun sendSMS(context: Context, phoneNo: String?, msg: String?) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
            Toast.makeText(
                context, "Message Sent!",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                context, ex.message.toString(),
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }
    }
}