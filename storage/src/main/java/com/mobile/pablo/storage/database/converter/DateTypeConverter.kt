package com.mobile.pablo.storage.database.converter

import androidx.room.TypeConverter
import java.util.Date

object DateTypeConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? =
        if (dateLong == null) null else Date(dateLong)

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time
}
