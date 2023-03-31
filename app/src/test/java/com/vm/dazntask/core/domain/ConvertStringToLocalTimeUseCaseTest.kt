package com.vm.dazntask.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Month
import java.time.ZoneId
import java.time.ZoneOffset

class ConvertStringToLocalTimeUseCaseTest {

    private val convertString = ConvertStringToLocalTimeUseCase()

    @Test
    fun `When local time zone is UTC, parses the same time`() {
        val dateString = "2023-03-29T01:29:36.324Z"
        val dateTime = convertString(dateString, ZoneId.of("UTC"))
        assertEquals(2023, dateTime.year)
        assertEquals(Month.MARCH, dateTime.month)
        assertEquals(29, dateTime.dayOfMonth)
        assertEquals(1, dateTime.hour)
        assertEquals(29, dateTime.minute)
        assertEquals(36, dateTime.second)
    }

    @Test
    fun `When local time zone is not UTC, converts the time to the time zone`() {
        val dateString = "2023-03-29T01:29:36.324Z"
        val dateTime =
            convertString(dateString, ZoneId.ofOffset("UTC", ZoneOffset.ofHoursMinutes(3, 30)))
        assertEquals(2023, dateTime.year)
        assertEquals(Month.MARCH, dateTime.month)
        assertEquals(29, dateTime.dayOfMonth)
        assertEquals(4, dateTime.hour)
        assertEquals(59, dateTime.minute)
        assertEquals(36, dateTime.second)
    }
}
