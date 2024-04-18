package com.andrei.birthdayreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDateTime
import java.time.ZoneId

class BirthdayAlarm(private val context: Context) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(contact: Contact) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("contact", contact)
        }

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel() {}
}