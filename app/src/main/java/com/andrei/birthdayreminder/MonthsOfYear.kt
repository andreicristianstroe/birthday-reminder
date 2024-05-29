package com.andrei.birthdayreminder

object MonthsOfYear {
    fun listOfMonths() = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
}

fun List<String>.nameOfMonthsToIndex(month: String): Int {
    return this.indexOf(month) + 1
}