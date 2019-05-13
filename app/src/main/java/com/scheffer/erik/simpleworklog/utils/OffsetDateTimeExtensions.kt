package com.scheffer.erik.simpleworklog.utils

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

fun OffsetDateTime.getDefaultDateString(): String =
        this.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.getDefault()))

fun OffsetDateTime.getDefaultTimeString(): String =
        this.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.getDefault()))