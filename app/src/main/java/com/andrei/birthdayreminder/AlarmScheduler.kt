package com.andrei.birthdayreminder

interface AlarmScheduler {
    fun schedule(contact: Contact)
    fun cancel()
}