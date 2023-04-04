package com.tobuy.persistence.database

import androidx.room.TypeConverter
import java.time.Instant
import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)

class GeneralTypeConverters {
    // region Instant
    @TypeConverter
    fun ser(instant: Instant): Long = instant.epochSecond

    @TypeConverter
    fun instant(epochSecond: Long): Instant = Instant.ofEpochSecond(epochSecond)
    // endregion

    @TypeConverter
    fun ser(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun uuid(string: String): UUID = string.toUUID()
}
