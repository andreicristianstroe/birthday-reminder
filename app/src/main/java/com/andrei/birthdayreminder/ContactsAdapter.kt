package com.andrei.birthdayreminder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.time.LocalDateTime

class ContactsAdapter(
    private val context: Context,
    private val data: List<Contact>?,
    private val data2: List<String>?
) :
    BaseAdapter() {
    var contactList = 0
    private var name: TextView? = null
    private var phoneNumber: TextView? = null
    private var birthday: TextView? = null
    private var months: TextView? = null
    override fun getCount(): Int {
        return if (contactList == 0) {
            data?.size ?: 0
        } else {
            data2?.size ?: 0
        }
    }

    override fun getItem(position: Int): Any {
        return data?.get(position) ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (contactList == 0) {
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.activity_list_view_components, parent, false)
        } else {
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.activity_months, parent, false)
        }
        name = view.findViewById(R.id.textView_name)
        phoneNumber = view.findViewById(R.id.textView_phoneNumber)
        birthday = view.findViewById(R.id.textView_birthday)
        months = view.findViewById(R.id.months)
        if (contactList == 0) {
            bindContacts(
                data?.get(position)?.name, data?.get(position)?.phoneNumber,
                data?.get(position)?.birthDay
            )
        } else {
            bindMonths(data2?.get(position)!!)
        }

        return view
    }

    private fun bindContacts(name: String?, phoneNumber: String?, birthdayAlarm: LocalDateTime?) {
        this.name?.text = name
        this.phoneNumber?.text = phoneNumber
        this.birthday?.text = birthdayAlarm.toString()
    }

    private fun bindMonths(months: String) {
        this.months?.text = months
    }

}