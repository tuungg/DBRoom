package paba.c14220130.projectroom.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {
    fun getCurrentDate() : String {
        val dateFormat = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss",
            Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }
}