package com.andrei.birthdayreminder

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.andrei.birthdayreminder.MonthsOfYear.listOfMonths
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private var contactList: ArrayList<Contact> = ArrayList()
    private lateinit var listView: ListView
    private lateinit var adapter: ContactsAdapter

    private val projection = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        val scheduler = BirthdayAlarm(this)
        getContactList()
        contactList.setContactBirthdayByMonth()
        scheduler.schedule(Contact("Andrei-Cristian Stroe", "5556", LocalDateTime.now()))
        adapter = ContactsAdapter(this, null, listOfMonths())
        adapter.contactList = 1
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, _, _ ->
            if(adapter.contactList == 1) {
                adapter = ContactsAdapter(this, contactList, null)
                adapter.contactList = 0
                listView.adapter =  adapter
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if(adapter.contactList == 0) {
            adapter = ContactsAdapter(this, null,  listOfMonths())
            adapter.contactList = 1
            listView.adapter = adapter
        } else {
            finish()
        }
    }
    private fun getContactList() {
        val cr = contentResolver
        val cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        if (cursor != null) {
            val mobileNoSet = HashSet<String>()
            cursor.use { mainCursor ->
                val nameIndex = mainCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberIndex =
                    mainCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                var name: String
                var number: String
                while (mainCursor.moveToNext()) {
                    name = mainCursor.getString(nameIndex)
                    number = mainCursor.getString(numberIndex)
                    number = number.replace(" ", "")
                    if (!mobileNoSet.contains(number)) {
                        contactList.add(Contact(name, number, LocalDateTime.now()))
                        mobileNoSet.add(number)
                    }
                }
            }
        }
    }

    private fun ArrayList<Contact>.setContactBirthdayByMonth() {
        this.forEach { contact ->
            when {
                contact.name.lowercase().startsWith("B") -> {
                }

                contact.name.lowercase().startsWith("C") -> {
                }

                contact.name.lowercase().startsWith("D") -> {
                }

                contact.name.lowercase().startsWith("E") -> {
                }

                contact.name.lowercase().startsWith("F") -> {
                }

                contact.name.lowercase().startsWith("G") -> {
                }

                contact.name.lowercase().startsWith("H") -> {
                }

                contact.name.lowercase().startsWith("I") -> {
                }
            }
        }
    }
}