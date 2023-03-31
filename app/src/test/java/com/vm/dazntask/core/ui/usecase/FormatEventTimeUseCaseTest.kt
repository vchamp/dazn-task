package com.vm.dazntask.core.ui.usecase

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class FormatEventTimeUseCaseTest {

    private val formatEventTime = FormatEventTimeUseCase()

    @Test
    fun `When time is yesterday, includes Yesterday string`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 10, 0)
        val yesterday = LocalDateTime.of(2023, Month.MARCH, 28, 20, 0)
        assertTrue(formatEventTime(yesterday, now).contains("Yesterday"))
    }

    @Test
    fun `When time is today, includes Today string`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 20, 0)
        val today = LocalDateTime.of(2023, Month.MARCH, 29, 10, 0)
        assertTrue(formatEventTime(today, now).contains("Today"))
    }

    @Test
    fun `When time is not yesterday or today, does not include Yesterday and Today strings`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 20, 0)
        val day = LocalDateTime.of(2023, Month.MARCH, 25, 10, 0)
        val formatted = formatEventTime(day, now)
        assertTrue(!formatted.contains("Today") && !formatted.contains("Yesterday"))
    }
}
