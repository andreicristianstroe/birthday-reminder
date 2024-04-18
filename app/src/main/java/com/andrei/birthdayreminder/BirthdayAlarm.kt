package com.andrei.birthdayreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId

class BirthdayAlarm(private val context: Context) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(contact: Contact) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("contact", contact)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        if (!alarmManager.canScheduleExactAlarms()) {
            requestExactAlarmPermission()
        } else {
            try {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                    pendingIntent
                )
            } catch (e: SecurityException) {
                Log.e("Alarm", "Failed to schedule exact alarm", e)
            }
        }
    }

    override fun cancel() {}

    private fun requestExactAlarmPermission() {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        context.startActivity(intent)
    }
}
